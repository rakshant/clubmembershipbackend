var app = angular.module("profile");

app.controller('viewBillingDetailsCtrl', function($scope, $http, $state, url) {
	$http({
		method : 'GET',
		url : url + 'viewbillingdetails' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		
		$scope.viewBillingDetails = data;
		console.log(data[0]);
	
	});
});