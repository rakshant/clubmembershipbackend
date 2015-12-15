var app=angular.module("home");

app.controller('HandleRegistrationCtrl', function($scope, $state,$http,$rootScope,url,$uibModal) {	
		function refresh(){
			$http({
				method : 'GET',
				url : url+'users/pendingrequests/'+localStorage.getItem('userId')		
			}).success(function(data) {
				$scope.personalDetails = data;	
				$rootScope.count=$scope.personalDetails.length;
				console.log($rootScope.count);
				
			});	
		}
		refresh();
		$scope.accept=function(id){
			$http({
				method : 'GET',
				url : url+'users/request?email='+id+'&status=accept'		
			}).success(function(data) {
				refresh();	
				
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "Email sent to the User"

						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
				
				
			});
		}
		$scope.reject=function(id){
			$http({
				method : 'GET',
				url : url+'users/request?email='+id+'&status=reject'		
			}).success(function(data) {
				
				$uibModal.open({
					templateUrl : 'views/modal.html',
					controller : function($scope, $uibModalInstance) {
						$scope.message = "Membership Rejected"

						$scope.ok = function() {
							$uibModalInstance.close();
						}
					}
				});
				refresh();		
				
				
				
			});
		}
});
