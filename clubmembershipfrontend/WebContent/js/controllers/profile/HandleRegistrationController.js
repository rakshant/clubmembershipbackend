var app=angular.module("home");

app.controller('HandleRegistrationCtrl', function($scope, $state,$http,$rootScope,url) {	
		function refresh(){
			$http({
				method : 'GET',
				url : url+'viewrequests/'+localStorage.getItem('userId')		
			}).success(function(data) {
				$scope.personalDetails = data;	
				$rootScope.count=$scope.personalDetails.length;
				console.log($rootScope.count);
				
			});	
		}
		refresh();
		$scope.accept=function(id){
			$http({
				method : 'GET',
				url : url+'processrequest?email='+id+'&status=accept'		
			}).success(function(data) {
				refresh();			
			});
		}
		$scope.reject=function(id){
			$http({
				method : 'GET',
				url : url+'processrequest?email='+id+'&status=reject'		
			}).success(function(data) {
				refresh();			
			});
		}
});


//?id='+localStorage.getItem("userId").trim()