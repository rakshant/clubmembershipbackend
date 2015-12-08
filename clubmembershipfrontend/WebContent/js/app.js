var myApp = angular.module("app", ['ui.bootstrap', 'ui.router']);

myApp.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "views/home/home.html"
        })
        .state('profile', {
            url: "/profile",
            templateUrl: "views/profile/profile.html"
        })
    .state('profile.nexreasdfa', {
        url: "/profilesdfsdf",
        templateUrl: "views/profile/sdfprofile.html"
    });


    $urlRouterProvider.otherwise("/home");
});
