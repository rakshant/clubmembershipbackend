var app=angular.module("home");

app.controller('ViewUsersCtrl', function($scope,$http,url) {	
	
	$http({
		method : 'GET',
		url : url+'users/active'	
	}).success(function(data) {
		$scope.userDetails = data;		
		
	});	
});


