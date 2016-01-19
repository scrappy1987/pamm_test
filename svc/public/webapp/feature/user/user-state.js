angular.module("pamm").config(function ($stateProvider) {
    $stateProvider.state("user", {
        abstract: true,
        views: {
            "root-content@": {
                templateUrl: "feature/user/user.html"
            }
        }
    }).state("user.home", {
        url: "/user/home",
        views: {
            "feature": {
                templateUrl: "feature/user/home/home.html"
            }
        }
    })
});
