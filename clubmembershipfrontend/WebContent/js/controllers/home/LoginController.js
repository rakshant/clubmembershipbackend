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
			console.log("----->"+data.userType);
			$scope.profileType = data.userType;
			console.log("----->"+data.response);

			localStorage.setItem('userType', $scope.profileType);
			localStorage.setItem('userId', data.id);
			$state.go('profile.viewDetails');			
		});
	}

});