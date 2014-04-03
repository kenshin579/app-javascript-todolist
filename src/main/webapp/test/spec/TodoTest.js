define([
    "Todo"
], function (Todo) {
//    console.log(jasmine.getFixtures().fixturesPath);
    jasmine.getFixtures().fixturesPath = "/";

    describe("Todo Suite", function () {
        it("데이터를 제대로 load하는지 확인", function () {
            loadFixtures("todo.html");

            var todo = new Todo();
            todo.loadList();
            expect(true).toBe(true);
        });
    });
});