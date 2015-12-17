var app = angular.module("profile");

app.controller('PanelListCtrl', function($scope,$http, url) {
	$http({
		method : 'GET',
		url : url + 'users' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		$scope.viewDetails = data;
		console.log($scope.viewDetails);
	});
	$scope.checkSecretary = function(key) {
		return localStorage.getItem(key).trim() === "Secretary";
	}
	$scope.checkUser = function(key) {
		return localStorage.getItem(key).trim() === "User";
	}
	
	$scope.checkPresident = function(key) {
		return localStorage.getItem(key).trim() === "President";			
	}
	
	$scope.checkPermanentUser = function(key) {
		return  localStorage.getItem(key).trim() === "permanent";
	}
	
	
	$scope.checkTreasurer = function(key) {
		return localStorage.getItem(key).trim() === "Treasurer";			
	}
	
	$scope.checkLink=function(){
		if(localStorage.getItem(key).trim() === "User")
			return true;
		else
			return false;
	}
});

