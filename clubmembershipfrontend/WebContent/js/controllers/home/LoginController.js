var app=angular.module("home");

app.controller('LoginCtrl', function($scope, $http, $state, $rootScope,url) {

	$scope.loginCheck = function() {
		$http({
			method : 'post',
			url : url+'login',
			data : {
				'emailId' : $scope.emailId,
				'password' : $scope.password,
			}
		}).success(function(data) {
			console.log("----->"+data);
			$scope.profileType = data.response;
			console.log("----->"+data.response);

			localStorage.setItem('userType', $scope.profileType);
			$state.go('profile');			
		});
	}

});