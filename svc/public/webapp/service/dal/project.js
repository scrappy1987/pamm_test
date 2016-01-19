"use strict";

angular.module("pamm").service("projectDao", ["dal", "$log", function (dal, $log) {
    this.getUserProjects = function (userId) {
        return dal.http.GET("user/" + userId + "/project");
    };

    this.saveProject = function (projectToSave) {
        return dal.http.POST("project", projectToSave);
    };

    this.updateProject = function (projectToUpdate) {
        return dal.http.PUT("project", projectToUpdate);
    };

    $log.debug("dal:projectDao Instantiated");
}]);
