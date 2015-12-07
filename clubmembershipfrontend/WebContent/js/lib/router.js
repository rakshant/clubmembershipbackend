var app=angular.module("RoomApp");

app.config(function($stateProvider,$urlRouterProvider){
	$stateProvider.state('home',{
		url:'/home',
		templateUrl:'views/home.html'
	});
	$urlRouterProvider.otherwise('/home');
});