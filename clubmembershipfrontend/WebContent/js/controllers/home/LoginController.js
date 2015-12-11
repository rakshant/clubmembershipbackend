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
			if(data.id==="failure"){
				alert("done man!")
			}else{
				localStorage.setItem('userType', data.userType);
				localStorage.setItem('userId', data.id);
				$state.go('profile.viewDetails');
			}				
		});
	}

});