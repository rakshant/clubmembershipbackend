var app=angular.module("home");

app.controller('RegisterationCtrl', function($scope, $http,url,$uibModal) {
	$scope.submit = function() {
		$http({
			method : 'post',
			url : url+'users/register',			
			data : {
				'firstName' : $scope.firstName,
				'lastName' : $scope.lastName,
				'emailId' : $scope.email,
				'dateOfBirth' : $scope.date,
				'mobileNumber' : $scope.mobileNo,
				'occupation' : $scope.occupation,
			}
		}).success(function(data){
			 $scope.firstName="";
			 $scope.lastName="";
			 $scope.email="";
			 $scope.date="";
			 $scope.mobileNo="";
			 $scope.occupation="";			 
		}) ;
		

		$uibModal.open({
		      templateUrl: 'views/modal.html',
		      controller: function ($scope,$uibModalInstance) {
		    	  $scope.message="We have received your registration request, we'll get in touch with you"
		    	  
		        $scope.ok=function(){
		        	$uibModalInstance.close();
		        	}
		      }});
		
	}
});