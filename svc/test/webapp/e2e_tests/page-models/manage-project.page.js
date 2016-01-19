"use strict";

module.exports = (function () {
    function ManageProjectPage() {
        this.projects = element.all(by.repeater('project in projectCtrl.projects'));
    }

    ManageProjectPage.prototype.visitPage = function () {
        return browser.get("/#/dashboard");
    };

     ManageProjectPage.prototype.getProjects = function () {
        return this.projects;
     };

     ManageProjectPage.prototype.currentURL = function (){
          return browser.getCurrentUrl();
      };

    return ManageProjectPage;
})();

