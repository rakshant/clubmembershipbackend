var myApp = angular.module("app", [ 'ui.bootstrap', 'ui.router','home','profile']);
myApp.constant("url","http://localhost:8080/");
myApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state('home', {
		url : "/home",
		templateUrl : "views/home/home.html"
	}).state('profile', {
		url : "/profile",
		templateUrl : "views/profile/profile.html"
	}).state('profile.viewDetails', {
		url : "/viewDetails",
		templateUrl : "views/profile/profileViews/viewDetails.html"
	}).state('profile.updateDetails', {
		url : "/update",
		templateUrl : "views/profile/profileViews/update.html"
	}).state('profile.registrationRequest', {
		url : "/registrationRequest",
		templateUrl : "views/profile/profileViews/registrationRequest.html"
	});

	$urlRouterProvider.otherwise("/home");
});
