var myApp = angular.module("app", [ 'ui.bootstrap', 'ui.router' ]);

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
	});

	$urlRouterProvider.otherwise("/home");
});

//
// $stateProvider
// .state('contacts', {
// url: '/contacts',
// ...
// })
// .state('contacts.list', {
// url: '/list',
// ...
// });
