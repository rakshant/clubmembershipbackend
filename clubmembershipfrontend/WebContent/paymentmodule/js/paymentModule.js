      
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope) {
    $scope.nameOnCard = 'Akshay Pathade';
    $scope.cardNumber = 5123456789012346;
	$scope.cardVerificationValue = 100;
	$scope.month = 10;
	$scope.year = 2015;
	$scope.amount = 1600;
});
