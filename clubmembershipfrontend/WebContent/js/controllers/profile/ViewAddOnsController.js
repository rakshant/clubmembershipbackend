
var app = angular.module("profile");
app.controller('viewAddOnsCtrl', function($scope, $http, $state, url) {
	
	$http({
		method : 'GET',
		url : url + 'users/addons' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		
		$scope.viewAddOns = data;

	});
	

});