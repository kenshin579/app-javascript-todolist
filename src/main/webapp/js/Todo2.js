define([
    "jquery",
    "sender"
], function ($, sender) {
    var Todo = function () {
        console.log("creating todo");
    };


    return Todo;
});

//function marshalling(listData /*String*/) {
//    console.log("marshalling");
//    //[{"text":"todo3","done":false},{"text":"todo2","done":false},{"text":"todo1","done":true}]
//    var retListData = [];
//    JSON.parse(listData, function (key, value) {
//        if (key == 'text') {
//            retListData.push({key: value});
//        }
//    });
//    console.log(JSON.stringify(retListData));
//    return JSON.stringify(retListData);
//}
//
//function send() {
//    var listData = window.localStorage.getItem("listData");
//    console.log("send: " + listData);
//    var inputData = {"todo": listData};
//    console.log("inputData: " + inputData);
//
//    $.ajax({
//        url: 'Sync',
//        type: 'POST',
//        data: inputData,
//        dataType: 'json',
//        success: function (data) {
//            console.log(data);
//            if (data)
//                $("#msg").text("Validation Success");
//            else
//                $("#msg").text("Validation Failure");
//        },
//        error: function (xhr) {
//            if (xhr.statusText != "abort" && xhr.status != 503) {
//                console.error("xhr error!!");
//            }
//        }
//    });
//}
//
//function addNewItem(text, /*Boolean*/ done) {
////    var newItem = $('<div class="item"><input type="checkbox" class="done"><input type="text" class="text"></div>');
//    var newItem = $('<div class="row"><div class="col-lg-12"><div class="input-group"><span class="input-group-addon">' +
//        '<input type="checkbox" class="done"></span><input type="text" placeholder="할일을 입력하세요" class="form-control text"></div></div></div>');
//    $('#list').prepend(newItem);
//
//    //��: �տ� �� dot�� �ٴ°�?
//    //A: class selector in jquery
//    newItem.find('.done').get(0).checked = done; //üũ�ڽ� �Ӽ��� üũ��
//    newItem.find('.text').val(text);
//    newItem.find('.text').focus();
//    //any text, done
//    newItem.find('.text, .done').change(saveList);
//}
//
//function cleanupList() {
//    $('#list .item .done:checked').parent().remove();
//
//    $('#list .item .text').filter(function (index, inputBox) {
//        return inputBox.value == "";
//    }).parent().remove();
//
//    saveList();
//}
//
//function loadList() {
////            var listData = window.localStorage.getItem("listData");
//    var listData = null;
//
//    $.ajax({
//        url: 'loadData',
//        type: 'POST',
//        data: '',
//        dataType: 'json',
//        success: function (data) {
//            listData = data;
//            for (var i = 0; i < listData.length; i++) {
//                listData[i].done = false;
//            }
//
//            console.log("received: ", data);
//
//            if (!listData)
//                return;
//            for (var i = 0; i < listData.length; i++) {
//                var itemData = listData[i];
//                addNewItem(itemData.text, itemData.done);
//            }
//
////                    if (data)
////                        $("#msg").text("Validation Success");
////                    else
////                        $("#msg").text("Validation Failure");
//        },
//        error: function (data, status, er) {
//            console.log("error: " + data + " status: " + status + " er:" + er);
//        }
//    });
//
//
//}
//
//function saveList() {
//    var listData = [];
//    $('#list .item').each(function (index, item) {
//        listData.push({
//            text: $(item).find('.text').val(),
//            done: $(item).find('.done').get(0).checked
//        });
//    });
//    console.log("saved list data", listData);
//    listData = JSON.stringify(listData);
//    window.localStorage.setItem("listData", listData);
//}