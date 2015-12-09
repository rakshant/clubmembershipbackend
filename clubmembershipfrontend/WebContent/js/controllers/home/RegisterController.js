myApp.controller('RegisterationCtrl', function($scope, $http) {
	console.log("ctrl loaded");
	$scope.submit = function() {

		$http({
			method : 'post',
			url : 'http://localhost:8080/register',
			data : {
				'firstName' : $scope.firstName,
				'lastName' : $scope.lastName,
				'emailId' : $scope.email,
				'dateOfBirth' : $scope.date,
				'mobileNumber' : $scope.mobileNo,
				'occupation' : $scope.occupation,
			}

		});
	}

});