var app=angular.module("home");

app.controller('HandleRegistrationCtrl', function($scope, $state,$http,url) {	
		function refresh(){
			$http({
				method : 'GET',
				url : url+'viewrequests'		
			}).success(function(data) {
				$scope.personalDetails = data;		
				console.log(data[0].firstName);
			});	
		}
		refresh();
		$scope.accept=function(id){
			$http({
				method : 'GET',
				url : url+'processrequest?email='+id+'&status=accept'		
			});
			refresh();			
		}
		$scope.reject=function(id){
			$http({
				method : 'POST',
				url : url+'processrequest?email='+id+'&status=reject'		
			});
			refresh();			
		}
});


//?id='+localStorage.getItem("userId").trim()