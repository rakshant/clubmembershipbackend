var app=angular.module("home");

app.controller('LoginCtrl', function($scope, $http, $state, $rootScope,url,$uibModal) {
	
	$scope.password;

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
				
				
				$uibModal.open({
				      templateUrl: 'views/modal.html',
				      controller: function ($scope,$uibModalInstance) {
				    	  $scope.message="Invalid credentials"
				    	  
				        $scope.ok=function(){
				        	$uibModalInstance.close();
				        	}
				      }});
			}
			else{
				localStorage.setItem('userType', data.userType);
				localStorage.setItem('userId', data.id);				
				$state.go('profile.viewDetails');
			}				
		});
	}

});