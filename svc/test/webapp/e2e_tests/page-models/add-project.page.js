"use strict";

module.exports = (function () {

    function AddProject() {
        this.title = element(by.id('title'));
        this.summary = element(by.id('summary'));
        this.info = element(by.id('info'));
        this.save = element(by.xpath('/html/body/div/div/div/form/div[2]/button[1]'));
    }

    AddProject.prototype.addTitle = function (title) {
        return this.title.sendKeys(title);
    };

    AddProject.prototype.addSummary = function (summary) {
        return this.summary.sendKeys(summary);
    };

    AddProject.prototype.addInformation = function (info) {
        return this.info.sendKeys(info);
    };

    AddProject.prototype.saveProject = function () {
        return this.save.click();
    };

    AddProject.prototype.closeModal = function (buttonId) {
        var closeButton = element(by.id(buttonId));
        browser.wait(EC.visibilityOf(closeButton), 1000);
        return closeButton.click();
    };

    return AddProject;

})();
