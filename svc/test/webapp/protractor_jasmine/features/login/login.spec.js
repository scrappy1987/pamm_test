"use strict";


var LoginPage = require("../page-models/login.page.js");
describe("At login", function () {

    /**
     * As all tests need to login into the application; this has been put into standard
     * setup in the onprepare function in the webdriver configuration.
     */

     var page = new LoginPage;

     beforeEach(function() {
        page.visitPage();
        page.fillUsername("username");
        page.fillPassword("password");
        page.login();
     });

    it("Should navigate to dashboard after login with correct credentials", function () {
        expect(browser.getCurrentUrl()).toMatch("/dashboard");
    });
});
