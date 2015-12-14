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
		return localStorage.getItem(key).trim() === "User"
				|| localStorage.getItem(key).trim() === "permanent";
	}	
	
});

