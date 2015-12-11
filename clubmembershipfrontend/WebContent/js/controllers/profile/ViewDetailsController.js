var app = angular.module("profile");

app.controller('viewCtrl', function($scope, $http, $state, url) {
	$http({
		method : 'GET',
		url : url + 'viewdetails' + "/" + localStorage.getItem('userId'),
	}).success(function(data) {
		$scope.viewDetails = data;
	});

	$scope.update = function() {
		$http.post(url + 'update' + "/" + localStorage.getItem('userId'),{
				
				'mobileNumber' : $scope.mobileNumber,
				'occupation' : $scope.occupation,
				'password':$scope.password,			
			}
		).success(function(data) {
			
		});
	}
});