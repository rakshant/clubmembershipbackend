var app=angular.module("home");

app.controller('RenewalCtrl', function($scope, $state,$http,$rootScope,url,$uibModal) {	
	
	
	$scope.permanent = function() {
		return localStorage.getItem('userType').trim() === "User";
	}
	
	$scope.checkPermanent=function(){
		
		$http({
			method : 'GET',
			url : url+'users/membershipRenewal' + "/" + localStorage.getItem('userId')		
		}).success(function(data) {			
			
			if(data.status==="failure"){
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "You are not yet allowed to become permanent member"

						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
			}
			if(data.status ==="success"){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id='
						+localStorage.getItem('userId')+'&fee='+20000+ '&type=' + 'permanent', '_blank');
			}
			
		});	
	}
	
	
	$scope.checkTemporary=function(){
		$http({
			method : 'GET',
			url : url+'users/renewal' + "/" + localStorage.getItem('userId')		
		}).success(function(data) {
			if(data.status=="failure"){
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "You tenure as a member is still left."
						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
			}
			if(data.status=="success"){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id="'
						+ localStorage.getItem('userId') + '&fee=' + 1000 + '&type=' + 'entry', '_blank');
			}			
	});
	}		
});


