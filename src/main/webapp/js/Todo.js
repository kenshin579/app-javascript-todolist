define([
    "jquery",
    "sender"
], function ($, sender) {
    var Todo = function () {
        console.log("loading todo data");
    };

    Todo.prototype = {
        init: function () {
            $('#add-new').on("click", $.proxy(function () {
                console.log("click add new item");
                this.addNewItem("", false);

            }, this));

            $('#cleanup').on("click", $.proxy(function () {
                console.log("clicked clean up");
                this.cleanUpList();
            }, this));

            this.loadList();

        },
        loadList: function () {
            var self = this;

            sender.send('loadData', null, function (serverData) {
                console.log("loadData", serverData);
                for (var i = 0; i < serverData.length; i++) {
                    serverData[i].done = false;
                }

                if (!serverData)
                    return;

                for (var i = 0; i < serverData.length; i++) {
                    var itemData = serverData[i];
                    self.addNewItem(itemData.text, itemData.done);
                }
            });

        },
        saveList: function () {
            console.log("saveList");
            var listData = [];
            $('#list .item').each(function (index, item) {
                listData.push({
                    text: $(item).find('.text').val(),
                    done: $(item).find('.done').get(0).checked
                });
            });
            console.log("saved list data", listData);
            listData = JSON.stringify(listData);

            var inputData = {"todo": listData};
            console.log("inputData: " + inputData);

            sender.send('Sync', inputData);

        },
        cleanUpList: function () {
            console.log("clean");

            //selects all elements that are descendants of a given ancestor.
            $('#list .item .done:checked').parent().parent().parent().parent().remove();

            $('#list .item .text').filter(function (index, inputBox) {
                console.log("index: " + index + "inputBox: " + inputBox.value);
                return inputBox.value == "";
            }).parent().parent().parent().remove();

            this.saveList();
        },
        addNewItem: function (/*String*/ inputText, /*Boolean*/ done) {
            var newItem = $(
                '<div class="row item">' +
                    '<div class="col-lg-12">' +
                    '<div class="input-group">' +
                    '<span class="input-group-addon">' +
                    '<input type="checkbox" class="done">' +
                    '</span>' +
                    '<input type="text" placeholder="할일을 입력하세요" class="form-control text">' +
                    '</div></div></div>');
            $('#list').prepend(newItem);

            //A: class selector in jquery
            newItem.find('.done').get(0).checked = done;
            newItem.find('.text').val(inputText);
            newItem.find('.text').focus();
            newItem.find('.text, .done').change(this.saveList);
        }

    };

    return Todo;
});

