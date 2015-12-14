var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope,$http) {
    $scope.nameOnCard = 'Akshay Pathade';
    $scope.cardNumber = 5123456789012346;
	$scope.cardVerificationValue = 100;
	$scope.month = 10;
	$scope.year = 2015;	
	
	var str=location.search.split('&');		
	$scope.amount =parseInt(str[1].substring(4));
	var type=str[2].substring(5);

	
	$scope.pay=function(){
		
		if(type==="entry"){
			$http({method : 'put',
				url : 'http://localhost:8080/users/payment/'+str[0].substring(4)
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/success.html');
			});
			
		}
		else{			
			$http({method : 'put',
				url : 'http://localhost:8080/users/facilities/'+str[0].substring(4),
				data : {
					'price' : $scope.amount,
					'type':str[2].substring(5),
					'category':str[3].substring(9)
				}
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/success.html','_self');

			});
		}	
	}
		
});
