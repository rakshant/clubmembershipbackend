var app=angular.module("profile");

app.controller('viewCtrl', function($scope, $http, $state,url) {

	$http.get(url+'viewdetails').success(function(result) {	
		$scope.personalDetails = result;
	});
});