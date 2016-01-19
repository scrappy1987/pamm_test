describe('Login-controller spec', function() {

    beforeEach(module('app'));

    var $controller, controller;
    var $state, securityManager;
    var $q, deferred, response;
    var scope;
    var sseConnectionManager;

    //To jasmine test promises, use the below example.
    var promise = function(response, resolved) {
        deferred = $q.defer();
        if (resolved == true) {
            deferred.resolve(response);
        }
        else {
            deferred.reject(response);
        }
        return deferred.promise;
    };

    beforeEach(inject(function($rootScope, _$controller_, _$state_, _securityManager_, _sseConnectionManager_, _$q_) {
        $q = _$q_;
        scope = $rootScope.$new();
        $state = _$state_;
        securityManager = _securityManager_;
        sseConnectionManager = _sseConnectionManager_;
        $controller = _$controller_;
        controller = $controller('loginController as loginCtrl', { $scope: scope, $state: $state, securityManager: securityManager, sseConnectionManager: sseConnectionManager });
    }));

    describe('Login method', function() {

        beforeEach(function() {
           scope.loginCtrl.username = "username";
            scope.loginCtrl.password = "password";
            //Initialise empty response
            response = {};
        });

        it('should set the session and go to the home state on promise success', function() {
            spyOn($state, 'go');
            spyOn(securityManager, 'login').and.returnValue(promise(response, true));
            scope.loginCtrl.login();
            scope.$apply();

            expect(securityManager.login).toHaveBeenCalledWith({username: "username", password: "password"});
            expect($state.go).toHaveBeenCalledWith('home.dashboard');
            expect(scope.loginCtrl.hasAuthenticationError).toBe(false);
            expect(scope.loginCtrl.hasValidationError).toBe(false);
        });

        it('should not set the session and go back to login state on promise failure', function() {
            spyOn($state, 'go');
            spyOn(securityManager, 'login').and.returnValue(promise(response, false));
            scope.loginCtrl.login();
            scope.$apply();

            expect(securityManager.login).toHaveBeenCalledWith({username: "username", password: "password"});
            expect($state.go).toHaveBeenCalledWith('login');
            expect(scope.loginCtrl.hasAuthenticationError).toBe(true);
            expect(scope.loginCtrl.hasValidationError).toBe(true);
        });
    });

});
