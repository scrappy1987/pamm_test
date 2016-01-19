"use strict";

var request = require('request');
module.exports = (function () {
    function SetupServiceCaller() {

    }

    SetupServiceCaller.prototype.executeScript = function (scriptNumber) {
        request({
           url: 'http://localhost:9001/execute-script/' + scriptNumber, //Run update sql script
           method: 'GET',
        })
    };

    return SetupServiceCaller;
})();