angular.module("pamm", ["ui.router", "googlechart", "ui.bootstrap", "ngAnimate", "ngMessages"]).run(
    function ($window, $rootScope, $log) {
        $log.debug("App Instantiated");
    }).config(
    function ($urlRouterProvider, $locationProvider) {
        var $log = angular.injector(["ng"]).get("$log");
        $urlRouterProvider.otherwise("user/login");
        $locationProvider.html5Mode(true);

        $log.debug("Configured HTML5 routing");
    }).provider("runtimeStates",
    function runtimeStates($stateProvider) {
        var $log = angular.injector(["ng"]).get("$log");
        this.$get = function () {
            return {
                addState: function (name, state) {
                    $stateProvider.state(name, state);
                }
            }
        };
        $log.debug("Configured RuntimeStates Provider");
    });

