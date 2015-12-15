var app=angular.module("home");

app.controller('FacilitiesCtrl', function($scope, $state,$http,$rootScope,url,$uibModal) {	
	
	$scope.checkPermanent=function(){
console.log("################################");
		
		$http({
			method : 'GET',
			url : url+'users/membershipRenewal' + "/" + localStorage.getItem('userId')		
		}).success(function(data) {
			if(data=="failure"){
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "You are not allowed to become permanent member"

						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
			}
			if(data=="success"){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id='
						+localStorage.getItem('userId')+'&fee='+20000+ '&type=' + 'permanent', '_blank');
			}
			
		});	
	}
	
	$scope.checkTemporary=function(){
		$http({
			method : 'GET',
			url : url+'users/membershipRenewal' + "/" + localStorage.getItem('userId')		
		}).success(function(data) {
			if(data=="failure"){
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "You tenure as temporary member is still left.. enjoy!!!"

						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
			}
			if(data=="success"){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id="'
						+ id + '&fee=' + 1000 + '&type=' + 'entry', '_blank');
			}
			
	});
	}
		
});


