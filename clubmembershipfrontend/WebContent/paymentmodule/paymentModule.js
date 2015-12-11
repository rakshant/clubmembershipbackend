 
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope,$http) {
    $scope.nameOnCard = 'Akshay Pathade';
    $scope.cardNumber = 5123456789012346;
	$scope.cardVerificationValue = 100;
	$scope.month = 10;
	$scope.year = 2015;
	$scope.amount = 1000;
	
	
	
	$scope.pay=function(){
		$http({method : 'get',
			url : 'http://localhost:8080/paymentdone/'+location.search.substring(4)
			
		});
		
		
		alert("your payment has been received, please check mail for password")
	}
		
});
