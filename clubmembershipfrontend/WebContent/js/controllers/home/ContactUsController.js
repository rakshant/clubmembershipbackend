var app = angular.module("home");
app.controller('contactUsCtrl', function($scope, $http, url, $state, $uibModal) {
	$scope.contact = function() {
		
		
		$uibModal.open({
			templateUrl : 'views/modal.html',
			controller : function($scope, $uibModalInstance) {
				
				
				var msg="Address: Sports Club Dempo House, Near Fun Park,Baner, Pune,(MH), India.403001" +
						"emailId: help.desk@sportsclub.com"
				$scope.message = msg
						
	
				$scope.ok = function() {
					$uibModalInstance.close();
				}
			}
		});
		
		
	}
	
	
});