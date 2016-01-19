"use strict";

var NavBar = require("../page-models/navigation-bar.page.js");
var AddProject = require("../page-models/add-project.page.js");
var LoginPage = require("../page-models/login.page.js");
var SetupServiceCaller = require("../util/setup-service-caller.js");

describe("When user selects sample from the navbar", function () {
    var navbar = new NavBar();
    var addProject = new AddProject();
    var setup = new SetupServiceCaller();
    var page = new LoginPage;

     beforeEach(function() {
        page.visitPage();
        page.fillUsername("username");
        page.fillPassword("password");
        page.login();
     });

    it("should navigate to manage project", function () {
        navbar.navigateToManageProject();
        expect(browser.getCurrentUrl()).toMatch("/project");
    });

    it("should navigate to add project", function () {
        navbar.navigateToAddProject();
        expect(browser.getCurrentUrl()).toMatch("/project/add");
    });

     it('Should navigate to add project and add given item - no items are populated in database prior', function() {
         setup.executeScript(2);
         navbar.navigateToAddProject();
         addProject.addProject("mock title", "mock summary", "mock info");
         addProject.closeModal('button-close');
         expect(browser.getCurrentUrl()).toMatch("/project");
         var todoList = element.all(by.repeater('project in projectCtrl.projects'));
         expect(todoList.count()).toEqual(1);
      });


});
