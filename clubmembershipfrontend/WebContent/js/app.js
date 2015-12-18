var myApp = angular.module("app", [ 'ui.bootstrap','ng-fusioncharts', 'ui.router','home','profile']);
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
	}).state('profile.userslist', {
		url : "/userslist",
		templateUrl : "views/profile/profileViews/userslist.html"
	}).state('profile.addons', {
		url : "/addons",
		templateUrl : "views/profile/profileViews/addons.html"
	}).state('profile.graph', {
		url : "/graph",
		templateUrl : "views/profile/profileViews/graph.html"
	}).state('profile.allocateBudget', {
		url : "/allocateBudget",
		templateUrl : "views/profile/profileViews/allocateBudget.html"
	}).state('profile.viewAddOns', {
		url : "/viewAddOns",
		templateUrl : "views/profile/profileViews/viewAddOns.html"
	}); 

	$urlRouterProvider.otherwise("/home");
});



/*
myApp.run(['$rootScope', '$location', 'Auth', function ($rootScope, $location, Auth) {
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            console.log('DENY');
            event.preventDefault();
            $location.path('profile.viewDetails');
        }
        else {
            console.log('ALLOW');
            $location.path('/home');
        }
    });
}]);


myApp.factory('Auth', function(){
	var user;

	return{
	    setUser : function(aUser){
	        user =localStorage.getItem('userId');
	    },
	    isLoggedIn : function(){
	        return(user)? user : false;
	    }
	  }
	})*/