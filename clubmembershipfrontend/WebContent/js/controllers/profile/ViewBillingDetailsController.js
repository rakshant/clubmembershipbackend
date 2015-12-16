var app = angular.module("profile");

app.controller('viewBillingDetailsCtrl', function($scope, $http, $state, url) {
	$http(
			{
				method : 'GET',
				url : url + 'users/bill' + "/"
						+ localStorage.getItem('userId'),
			}).success(function(data) {

		$scope.viewBillingDetails = data;


	});
});