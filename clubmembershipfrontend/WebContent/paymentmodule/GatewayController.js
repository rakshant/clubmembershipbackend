var app = angular.module('myApp', []);
app.controller('dataCtrl', function($scope,$http) {
	console.log("----------------------------->");
	
	var str=location.search.split('&');		
	$scope.amount =parseInt(str[1].substring(4));
	var type=str[2].substring(5);
	
	$scope.firstName=str[3].substring(10);
	$scope.lastName=str[4].substring(9);
	$scope.mobile=str[5].substring(6);
	$scope.occupation=str[7].substring(11);
	$scope.email=str[6].substring(7);
	
	console.log("----->"+$scope.firstName)
	
	
	$scope.proceed=function(data){
		window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id='
				+str[0].substring(4)+"&fee="+str[1].substring(4)+"&type="+str[2].substring(5),'_self');
	}		
});
