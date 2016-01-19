"use strict";

/**
 * The domain.repository manages holds the context of the current work item.
 */

angular.module("pamm").service("cacheManager", ["$log", "$rootScope", "$cacheFactory", "contextEvent",
    function ($log, $rootScope, $cacheFactory, contextEvent) {
        var cache = {};

        (function init() {
            $rootScope.$on(contextEvent.CLEAR_CONTEXT, function clearContext() {
                for (var i = 0; i < cache.length; i++) {
                    cache[i].removeAll();
                }
                $log.debug("cacheManager: context cleared");
            })
        })();

        this.get = function (cacheId) {
            if (cache[cacheId] == undefined) {
                cache[cacheId] = $cacheFactory(cacheId);
            }
            return cache[cacheId];
        };

        $log.debug("cacheManager: Instantiated");
    }]);
