"use strict";

module.exports = (function () {
    function NavBar() {
        this.projectLink = element(by.linkText("Sample"));
        this.manageProjectLink = element(by.linkText("Manage Project"));
        this.addProjectLink = element(by.linkText("Add New Project"));
    }

    NavBar.prototype.navigateToManageProject = function () {
        this.projectLink.click();
        this.manageProjectLink.click();
    };

    NavBar.prototype.navigateToAddProject = function () {
        this.projectLink.click();
        this.addProjectLink.click();
    };

    return NavBar;
})();
