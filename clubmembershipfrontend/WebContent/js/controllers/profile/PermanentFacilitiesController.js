var app=angular.module("home");

app.controller('PermanentFacilitiesCtrl', function($scope,$http,url) {	
	
	
	 $scope.facilities = [
	                  {category:'indoor', type: 'badminton', price:1000},
	                  {category:'outdoor', type: 'swimming', price:2000},
	                  {category:'leisure', type: 'hall', price:50000},
	                  {category:'indoor', type: 'tableTennis', price:300},
	                  {category:'indoor', type: 'squash', price:500}          
	               ];
	 
	 
	 console.log( $scope.facilities);
	 
	 
	 $scope.user = {
			 	facilities: [$scope.facilities[1]]
			  };
	
	  
});



