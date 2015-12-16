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
	}).state('profile.update', {
		url : "/update",
		templateUrl : "views/profile/profileViews/update.html"
	}).state('profile.registrationRequest', {
		url : "/registrationRequest",
		templateUrl : "views/profile/profileViews/registrationRequest.html"
	}).state('profile.reservedFacilityTemperaryMember', {
		url : "/reservedFacilityTemperaryMember",
		templateUrl : "views/profile/profileViews/reservedFacilityTemperaryMember.html"
	}).state('profile.viewBillingDetails', {
		url : "/viewBillingDetails",
		templateUrl : "views/profile/profileViews/viewBillingDetails.html"
	}).state('profile.renewal', {
		url : "/renewal",
		templateUrl : "views/profile/profileViews/renewal.html"
	}).state('profile.userlist', {
		url : "/userlist",
		templateUrl : "views/profile/profileViews/userlist.html"
	}).state('profile.addons', {
		url : "/addons",
		templateUrl : "views/profile/profileViews/addons.html"
	});

	$urlRouterProvider.otherwise("/home");
});
