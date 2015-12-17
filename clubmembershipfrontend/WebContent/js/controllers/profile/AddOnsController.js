var app=angular.module("home");

app.controller('AddOnsCtrl', function($scope, $state,$http,$rootScope,url,$uibModal) {	
	
	
	$scope.add = function() {
		
		
			if($scope.tempOption==="spouse"){
				var relation='spouse'
			}
			if($scope.tempOption==="son"){
				var relation='son'
			}
			if($scope.tempOption==="daughter"){
				var relation='daughter'
			}
		
			
			
			$http.put(url + 'users' + "/" +'addons' + "/" + localStorage.getItem('userId'), {
				'name' : $scope.name,
				'dob' : $scope.dob,
				'relation' :relation
			}).success(function(data) {
				
				$uibModal.open({
						templateUrl : 'views/modal.html',
						controller : function($scope, $uibModalInstance) {
							$scope.message = "Member Added in your account"

							$scope.ok = function() {
								
								
								
								$uibModalInstance.close();
							}
						}
					});
				 
			});		
		
		
		
		
		
		
	}
	
	
	
	$scope.showThis=function(){
		
		$scope.message = "You Have Already Added One Member"
		
		
	}
	
		
		
});


