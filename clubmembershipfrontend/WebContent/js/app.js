var myApp = angular.module("app", ['ui.bootstrap', 'ui.router']);

myApp.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "home.html"
        })
        .state('mome', {
            url: "/mome",
            templateUrl: "mome.html"
        })
        .state('mome.abc', {
            url: "/abc",
            template: "<h1>state1</h1>"
        }).state('mome.cde', {
            url: "/cde",
            template: "<h1>state2</h1>"
        });

    $urlRouterProvider.otherwise("/home");
});
