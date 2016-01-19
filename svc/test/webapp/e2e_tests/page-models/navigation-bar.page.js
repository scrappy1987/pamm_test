"use strict";

module.exports = (function () {
    function NavBar() {
        this.projectLink = element(by.linkText("Sample"));
        this.manageProjectLink = element(by.linkText("Manage Project"));
        this.addProjectLink = element(by.linkText("Add New Project"));
    }

    NavBar.prototype.clickSample = function () {
        return this.projectLink.click();
    };

    NavBar.prototype.clickManageProject = function () {
        return this.manageProjectLink.click();
    };

    NavBar.prototype.clickAddNewProject = function () {
        return this.addProjectLink.click();
    };

    return NavBar;
})();
