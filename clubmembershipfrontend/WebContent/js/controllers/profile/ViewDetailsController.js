var app = angular.module("profile");

app.controller('viewCtrl', function($scope, $http, $state, url) {
	$http({
		method : 'GET',
		url : url + 'users' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {

		$scope.viewDetails = data;
		console.log(data[0]);
		$scope.mobileNumber = data[0].mobileNumber;
		$scope.occupation = data[0].occupation;
		$scope.password = data[0].password;

		console.log($scope);
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