"use strict";

module.exports = (function () {

    function AddProject() {
        this.title = element(by.id('title'));
        this.summary = element(by.id('summary'));
        this.info = element(by.id('info'));
        this.save = element(by.xpath('/html/body/div/div/div/form/div[2]/button[1]'));
    }

    AddProject.prototype.addProject = function (title, summary, info) {
        this.title.sendKeys(title);
        this.summary.sendKeys(summary);
        this.info.sendKeys(info);
        this.save.click();
    };

    AddProject.prototype.closeModal = function (buttonId) {
        var closeButton = element(by.id(buttonId));
        browser.wait(EC.visibilityOf(closeButton), 1000);
        closeButton.click();
    };

    return AddProject;

})();
