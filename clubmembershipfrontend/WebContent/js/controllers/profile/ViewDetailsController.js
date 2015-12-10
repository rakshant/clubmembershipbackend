var app = angular.module("profile");

app.controller('viewCtrl', function($scope, $http, $state, url) {
	$http({
		method : 'GET',
		url : url + 'viewdetails' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		$scope.viewDetails = data;		
	});
});