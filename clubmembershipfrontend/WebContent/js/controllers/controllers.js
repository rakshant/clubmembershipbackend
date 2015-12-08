myApp.controller('CarouselDemoCtrl', function($scope) {
	$scope.myInterval = 2000;
	$scope.slides = [ {
		image : 'images\\ch1.jpg'
	}, {
		image : 'images\\ch2.jpg'
	}, {
		image : 'images\\ch3.jpg'
	}, ];
});

myApp.controller('TabsDemoCtrl', function($scope) {
	$scope.checkSecretary=function(key){
		return 'Secretary'===localStorage.getItem(key);
	}
	$scope.checkUser=function(key){
		return 'User'===localStorage.getItem(key) || 'PermanentUser'===localStorage.getItem(key);
	}
	console.log(localStorage.getItem("userType"))
});

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


myApp.controller('LoginCtrl', function($scope, $http,$state) {
	console.log("login loaded");
	$scope.loginCheck = function() {
		console.log("login check called!")
		$http({
			method : 'post',
			url : 'http://localhost:8080/login',
			data : {
				'emailId' : $scope.emailId,
				'password' : $scope.password,				
			}

		}).success(function(data){
			$scope.profileType=data;
			localStorage.setItem('userType',$scope.profileType);
			$state.go('profile.'+$scope.profileType);
			console.log("success");
		});
	}

});



myApp.controller('viewCtrl', function($scope, $http,$state) {	
	$scope.loginCheck = function() {
		
		$http({
			method : 'get',
			url : 'http://localhost:8080/viewdetails'
		}).success(function(data){
			$scope.personalDetails=data;			
			console.log($scope.personalDetails);
		});
	}

});