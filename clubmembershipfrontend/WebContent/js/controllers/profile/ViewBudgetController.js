
var app = angular.module("profile");
app.controller('budgetCtrl', function($scope, $http, $state, url) {	
	$http({
		method : 'GET',
		url : url + 'users' + "/" + 'budget',
	}).success(function(data) {
		$scope.budgetDetails=data;
		
	});
		
$scope.allocate = function() {
	$http.post(url + 'users' + "/" + 'budget', {
		'indoor' : $scope.indoor,
		'outdoor' : $scope.outdoor,
		'leisure' : $scope.leisure,
	}).success(function(data) {
	
		$scope.budgetDetails = data;
		
	});	
}
});