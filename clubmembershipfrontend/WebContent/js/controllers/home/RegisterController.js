var app=angular.module("home");

app.controller('RegisterationCtrl', function($scope, $http,url) {
	$scope.submit = function() {
		$http({
			method : 'post',
			url : url+'register',			
			data : {
				'firstName' : $scope.firstName,
				'lastName' : $scope.lastName,
				'emailId' : $scope.email,
				'dateOfBirth' : $scope.date,
				'mobileNumber' : $scope.mobileNo,
				'occupation' : $scope.occupation,
			}
		});
		
		alert("Your registration request has been received, we'll get in touch with you")
	}
});