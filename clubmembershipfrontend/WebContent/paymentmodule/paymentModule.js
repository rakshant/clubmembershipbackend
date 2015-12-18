var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope,$http) {

	
	var str=location.search.split('&');		
	$scope.amount =parseInt(str[1].substring(4));
	var type=str[2].substring(5);
	
	
	
	
	

	
		$scope.pay=function(){
		
		if(type==="entry"){
			$http({method : 'put',
				url : 'http://localhost:8080/users/payment/'+str[0].substring(4)+'/entry'
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/index.html','_self');
			});
			
		}
		
		
		else if(type==="permanent"){
			$http({method : 'put',
				url : 'http://localhost:8080/users/payment/'+str[0].substring(4)+'/permanent'				
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/index.html','_self');
			});
		}
		
		
		
		
		
		else if(type==="permanentFacility"){
			var data=JSON.parse(localStorage.getItem('facility'));
			var item=data.item;
			console.log("length"+item.length+"  "+item[0].id);
			var data={"item":[]};
				
				for(var i=0;i<item.length;i++){
					data.item.push({
						'price' : item[i].price,
						'type':item[i].type,
						'category':item[i].category
					});						
				}					
				console.log(data);
				$http({method : 'put',
					url : 'http://localhost:8080/users/facilities/'+str[0].substring(4)+'/permanent',	
					data:data
				}).success(function(data){
					window.open('http://localhost:8089/clubmembershipfrontend/index.html#/profile/viewDetails','_self');			
				});						
		}

		else
		{			
			$http({method : 'put',
				url : 'http://localhost:8080/users/facilities/'+str[0].substring(4)+'/temporary',
				data : {
				"item":[{
									'price' : $scope.amount,
									'type':str[2].substring(5),
									'category':str[3].substring(9)		
				}]
				}
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/index.html#/profile/viewDetails','_self');

			});
		}	
	}
	
	
	
	
	
		
});
