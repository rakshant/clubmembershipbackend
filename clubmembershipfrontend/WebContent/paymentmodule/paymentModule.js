 
var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope,$http) {
    $scope.nameOnCard = 'Akshay Pathade';
    $scope.cardNumber = 5123456789012346;
	$scope.cardVerificationValue = 100;
	$scope.month = 10;
	$scope.year = 2015;
	$scope.amount = 1000;
	
	var str=location.search.split('&');	
	$scope.amount =parseInt(str[1].substring(5));
	
	$scope.pay=function(){
		
		if($scope.amount === 1000){
			$http({method : 'get',
				url : 'http://localhost:8080/paymentdone/'+str[0].substring(4)
				
			});
			alert("your payment has been received, please check mail for password")
		}
		else{
			$http({method : 'post',
				url : 'http://localhost:8080/paymentFacilities/'+str[0].substring(4),
				data : {
					'price' : $scope.amount,
					'type':str[2].substring(10),
					'category':str[3].substring(10)
				}
				
			});
		}	
	}
		
});
