var app=angular.module("home");

app.controller('HandleRegistrationCtrl', function($scope, $http,url) {	
		$http({
			method : 'get',
			url : url+'viewrequests'		
		}).success(function(data) {
			$scope.personalDetails = data.response;		
			console.log($scope.personalDetails);
			$state.go('registrationRequest');			
		});	
});

