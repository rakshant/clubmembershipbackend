
var app = angular.module("profile");
app.controller('budgetCtrl', function($scope, $http, $state, url) {
	
	
	
	$http({
		method : 'GET',
		url : url + 'users/budget'
	}).success(function(data) {
		
		$scope.budgetDetails = data;
		
		
		console.log($scope.budgetDetails)

	});
	
});