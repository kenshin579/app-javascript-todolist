define([
    "jquery",
    "Todo",
    "sender"
], function ($, Todo, sender) {
//    console.log(jasmine.getFixtures().fixturesPath);
    jasmine.getFixtures().fixturesPath = "/";

    describe("Todo Suite", function () {
        beforeEach(function () {
            loadFixtures("todo.html");
        });

        it("데이터를 제대로 load하는지 확인", function () {

            //todo 작업중
            var todo = new Todo();
            var serverData = [
                {"text": "faf", "done": true},
                {"text": "new item", "done": true}
            ];
            spyOn(sender, 'send').andReturn(null, null, function (serverData) {
                console.log("spyOn");
            });

            todo.loadList();
            expect(sender.send).toHaveBeenCalled();

        });

        it("saveList가 제대로 되는지 확인", function () {

            //todo. 생성한 inputData를 체크하면 됨
            //참고: http://stackoverflow.com/questions/19440865/jasmine-jquery-test-to-check-if-correct-arguments-have-been-passed-to-a-method

            var todo = new Todo();
            var expectedInputData = { todo: '[{"text":"item2","done":false},{"text":"item1","done":false}]' };

            spyOn(sender, 'send');
            expect(0).toBe($("#list").children().length);

            todo.addNewItem("item1");
            todo.addNewItem("item2");

            expect(2).toBe($("#list").children().length);
            todo.saveList();

            expect(sender.send).toHaveBeenCalledWith('Sync', expectedInputData);

        });


        it("addNewItem 함수가 제대로 실행되는지 확인", function () {
            var todo = new Todo();
            var inputText = "new item";

            expect(0).toBe($("#list").children().length);

            todo.addNewItem(inputText, true);
            expect(1).toBe($("#list").children().length);
            expect(inputText).toBe($("#list").children().first().find('.text').val());
        });


    });
});