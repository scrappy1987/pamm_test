exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    capabilities: {
        browserName: 'chrome'
    },

    // CHANGE this to test server
    baseUrl: "http://localhost:9000",

    specs: [
        'features/**/*.feature'
    ],

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;
    },

    framework: 'cucumber',

    cucumberOpts: {
        require: 'features/**/stepDefinitions.js'
    }
};


