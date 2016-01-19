"use strict";

var request = require('request-promise');

module.exports = (function () {
    function SetupServiceCaller() {

    }

    SetupServiceCaller.prototype.executeScript = function (scriptNumber) {
         return request('http://localhost:9001/execute-script/' + scriptNumber)
    };

    return SetupServiceCaller;
})();
