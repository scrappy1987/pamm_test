"use strict";

module.exports = (function () {
    function LoginPage() {
        this.usernameField = element(by.model("loginCtrl.username"));
        this.passwordField = element(by.model("loginCtrl.password"));
        this.loginButton = element(by.buttonText("Login"));
    }

    LoginPage.prototype.visitPage = function () {
        browser.get("index.html");
    };

    LoginPage.prototype.fillUsername = function (username) {
        this.usernameField.sendKeys(username);
    };

    LoginPage.prototype.fillPassword = function (password) {
        if (password == null) {
            password = "password";
        }
        this.passwordField.sendKeys(password);
    };

    LoginPage.prototype.login = function () {
        this.loginButton.click();
    };


    return LoginPage;
})();

