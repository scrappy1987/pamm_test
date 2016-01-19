"use strict";

module.exports = (function () {
    function LoginPage() {
        this.usernameField = element(by.model("loginCtrl.username"));
        this.passwordField = element(by.model("loginCtrl.password"));
        this.loginButton = element(by.buttonText("Login"));
    }

    LoginPage.prototype.visitPage = function () {
        var url = browser.get("/");
        return url;
    };

    LoginPage.prototype.fillInDetails = function (username, password) {
        return this.usernameField.sendKeys(username) && this.passwordField.sendKeys(password);
    };

    LoginPage.prototype.login = function () {
        return this.loginButton.click();
    };

    LoginPage.prototype.currentURL = function (){
        return browser.getCurrentUrl();
    };

    return LoginPage;
})();

