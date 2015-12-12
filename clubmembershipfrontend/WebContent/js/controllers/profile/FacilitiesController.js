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
	if($scope.tempOption==="cardRoom"||$scope.tempOption==="library"||$scope.tempOption==="restaurant&Bar"||
			$scope.tempOption==="banquetHall"||$scope.tempOption==="conferenceHall"){
		var category='Leisure'
	}
	
		$scope.check=function(id){
			$http({
				method : 'POST',
				url : url+'getFee' + "/" + localStorage.getItem('userId'),
				data:{
					'facility' : $scope.tempOption,
					'category' :	category
				}
			}).success(function(data) {
					$scope.fee=data
			});

			$http({method : 'get',
				url : 'http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id:'
					+localStorage.getItem('userId')+'&fee:'+$scope.fee+'&facility:'+$scope.tempOption+'&category:'+category
				
			});			
		}
});


