var ManageProjects = require("../../page-models/manage-project.page.js");
var NavBar = require("../../page-models/navigation-bar.page.js");
var AddProject = require("../../page-models/add-project.page.js");
var SetupServiceCaller = require("../../util/setup-service-caller.js");
var assert = require('assert');

module.exports = function () {

    this.setDefaultTimeout(60000);
    var manageProjects = new ManageProjects();
    var addProject = new AddProject();
    var setup = new SetupServiceCaller();
    var nav = new NavBar();

    this.Given(/^I am on the dashboard$/, function (callback) {
        manageProjects.visitPage().then(function () {
            callback();
        })
    });

    this.Given(/^There are no projects$/, function (callback) {
        setup.executeScript(2).then(function() {
            callback();
        });
    });

    this.Given(/^I choose sample$/, function (callback) {
        nav.clickSample().then(function () {
            callback();
        })
    });

    this.When(/^I choose manage project$/, function (callback) {
        nav.clickManageProject().then(function () {
            callback();
        })
    });

    this.Then(/^There are no projects for me to manage$/, function (callback) {
        manageProjects.getProjects().then(function (elements) {
            assert.equal(elements.length, 0);
            callback();
        })
    });

    this.Given(/^There are three projects$/, function (callback) {
        setup.executeScript(1).then(function() {
            callback();
        })
    });

    this.Then(/^There are three projects for me to manage$/, function (callback) {
        manageProjects.getProjects().then(function (elements) {
            assert.equal(elements.length, 3);
            callback();
        })
    });

    this.When(/^I choose add new project$/, function (callback) {
        nav.clickAddNewProject().then(function () {
            callback();
        })
    });

    this.When(/^I enter a title$/, function (callback) {
      addProject.addTitle("mock-title").then(function () {
          callback();
      })
    });

    this.When(/^I enter a summary$/, function (callback) {
        addProject.addSummary("mock-summary").then(function () {
          callback();
        })
    });

    this.When(/^I enter information$/, function (callback) {
         addProject.addInformation("mock-information").then(function () {
          callback();
        })
    });

    this.When(/^I save the project$/, function (callback) {
       addProject.saveProject().then(function () {
          callback();
       })
    });

    this.When(/^I close the confirmation dialogue$/, function (callback) {
         addProject.closeModal('button-close').then(function () {
            callback();
         })
     });

    this.Then(/^There is now one project to manage$/, function (callback) {
      manageProjects.getProjects().then(function (elements) {
          assert.equal(elements.length, 1);
          callback();
      })
    });

};
