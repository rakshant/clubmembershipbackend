myApp.controller('LoginCtrl', function($scope, $http, $state, $rootScope) {

	$scope.loginCheck = function() {
		$http({
			method : 'post',
			url : 'http://localhost:8080/login',
			data : {
				'emailId' : $scope.emailId,
				'password' : $scope.password,
			}
		}).success(function(data) {
			$scope.profileType = data.response;
			localStorage.setItem('userType', $scope.profileType);
			$state.go('profile');
			console.log("success");
		});
	}

});