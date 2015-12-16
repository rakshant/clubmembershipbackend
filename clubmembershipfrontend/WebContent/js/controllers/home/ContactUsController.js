var app = angular.module("home");
app.controller('contactUsCtrl', function($scope, $http, url, $state, $uibModal) {
	$scope.contact = function() {
		
		
		$uibModal.open({
			templateUrl : 'views/modal.html',
			controller : function($scope, $uibModalInstance) {
				$scope.message =
						"Sports Club Dempo House, Near Fun Park,Baner, Pune,(MH), India.403001"+
						"help.desk@sportsclub.com"
	
				$scope.ok = function() {
					$uibModalInstance.close();
				}
			}
		});
		
		
	}
	
	
});