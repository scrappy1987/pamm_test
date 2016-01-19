module.exports = function (config) {
    config.set({
        frameworks: ['jasmine'],

        basePath: '../../../',

        preprocessors: {
            "public/webapp/feature/**/*.js": ['coverage'],
            "public/webapp/service/**/*.js": ['coverage'],
            "public/webapp/constant/**/*.js": ['coverage'],
            "public/webapp/directive/**/*.js": ['coverage'],
            "public/webapp/*.js": ['coverage']
        },

        files: [
            // Main libriaries dependencies
            "public/webapp/lib/lodash/lodash.min.js",
            "public/webapp/lib/jquery/jquery-2.1.4.min.js",
            "public/webapp/lib/angular-1.4.4/angular.min.js",
            "public/webapp/lib/ui-router/angular-ui-router.js",
            "public/webapp/lib/bootstrap-3.3.5-dist/js/bootstrap.min.js",
            "public/webapp/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js",

            <!-- Test Libraries (note angular-mocks.js needs to be loaded after real angular) -->
            "test/webapp/unit/lib/angular-1.4.4/angular-mocks.js",

            <!-- app dependencies required to run tests-->
            "public/webapp/service/repository/repository.js",
            "public/webapp/service/repository/project.js",
            "public/webapp/service/dal/dal.js",
            "public/webapp/constant/*.js",

            <!-- Mocks-->
            "test/webapp/mocks/service/security/security-manager.js",
            "test/webapp/mocks/service/security/auth-interceptor.js",
            "test/webapp/mocks/service/sse/sseConnectionManager.js",
            "test/webapp/mocks/service/dal/dal.js",
            "test/webapp/mocks/service/dal/project.js",
            "test/webapp/unit/app.js",

            <!-- Features being tested -->
            {pattern: 'public/webapp/feature/**/*.js'},
            {pattern: 'public/webapp/service/**/*.js'},
            {pattern: 'test/webapp/unit/feature/**/*.spec.js'},
            {pattern: 'test/webapp/unit/service/**/*.spec.js'}

        ],
        // list of files to exclude
        exclude: [],

        // web server port
        port: 9876,

        // enable / disable colors in the output (reporters and logs)
        colors: true,

        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // start these browsers when tests are launched
        browsers: ['Chrome'],

        // test results reporter to use
        reporters: ['progress', 'coverage'],

        coverageReporter: {
            type: 'html',
            dir: 'test/webapp/unit/reports/karma/coverage/'
        },

        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: true
    });
};
