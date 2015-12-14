var app = angular.module("profile");

app.controller('viewCtrl', function($scope, $http, $state, url) {
	$http({
		method : 'GET',
		url : url + 'users' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		
		$scope.viewDetails = data;		
		$scope.mobileNumber = data.mobileNumber;
		$scope.occupation = data.occupation;
		$scope.password = data.password;

	});

	$scope.update = function() {
		$http.put(url + 'users' + "/" + localStorage.getItem('userId'), {

			'mobileNumber' : $scope.mobileNumber,
			'occupation' : $scope.occupation,
			'password' : $scope.password,
		}).success(function(data) {

		});
	}
});