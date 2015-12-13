var app=angular.module("home");

app.controller('FacilitiesCtrl', function($scope, $state,$http,$rootScope,url) {	
	
	
	if($scope.tempOption==="tableTennis"||$scope.tempOption==="badminton"||$scope.tempOption==="billiards"||
			$scope.tempOption==="healthClub"||$scope.tempOption==="squash"){
		var category='Indoor'
	}
	if($scope.tempOption==="lawnTennis"||$scope.tempOption==="swimming"||$scope.tempOption==="cricket"||
			$scope.tempOption==="playground"){
		var category='Outdoor'
	}
	if($scope.tempOption==="cardRoom"||$scope.tempOption==="library"||$scope.tempOption==="restaurantBar"||
			$scope.tempOption==="banquetHall"||$scope.tempOption==="conferenceHall"){
		var category='Leisure'
	}
	
		$scope.check=function(id){
			$http({
				method : 'GET',
				url : url+'getFee' + "/" + localStorage.getItem('userId')+"/"+$scope.tempOption,
				/*data:{
					'category' :	category,
					'type' : $scope.tempOption
				}*/
			}).success(function(data) {
					$scope.fee=data
			});

			$http({method : 'get',
				url : 'http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id:'
					+localStorage.getItem('userId')+'&fee:'+$scope.fee+'&type:'+$scope.tempOption+'&category:'+category
				
			});			
		}
});


