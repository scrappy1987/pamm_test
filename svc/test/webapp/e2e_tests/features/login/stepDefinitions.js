// Do login for each test

var LoginPage = require("../../page-models/login.page.js");
var assert = require('assert');

module.exports = function () {

    this.setDefaultTimeout(60000);
    var login = new LoginPage;

    this.Given(/I am on the login view/, function (callback) {
        login.visitPage().then(function () {
            callback();
        });
    });

    this.Given(/^I supply valid credentials$/, function (callback) {
        login.fillInDetails("admin", "password").then(function () {
            callback();
        })
    });

    this.When(/^I login$/, function (callback) {
        login.login().then(function () {
            callback();
        });
    });

    this.Then(/^I see the dashboard$/, function (callback) {
        login.currentURL().then(function (currentURL) {
            assert.equal("http://localhost:9000/#/dashboard", currentURL);
            callback();
        });
    });

};
