var app = angular.module('myApp', []);
app.controller('validateCtrl', function($scope,$http) {
    $scope.nameOnCard = '';
    $scope.cardNumber = '';
	$scope.cardVerificationValue = '';
	$scope.month = '';
	$scope.year = '';	
	
	var str=location.search.split('&');		
	$scope.amount =parseInt(str[1].substring(4));
	var type=str[2].substring(5);
	
	
	var data=JSON.parse(localStorage.getItem('facility'));
	var item=data.item;
	
	
	
	console.log("length"+item.length+"  "+item[0].id);
	

	
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
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/success.html');
			});
		}
		
		
		
		
		
		else if(type==="permanentFacility"){
			
			for(var i=0;i<item.length;i++){
				$http({method : 'put',
					url : 'http://localhost:8080/users/facilities/'+str[0].substring(4)+'/permanent',	
					
					data:{
						'price' : item[i].price,
						'type':item[i].type,
						'category':item[i].category
					}

				});
						
				
						
			}	
			
			window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/success.html','_self');
		}

		else
		{			
			$http({method : 'put',
				url : 'http://localhost:8080/users/facilities/'+str[0].substring(4)+'/temporary',
				data : {
				
									'price' : $scope.amount,
									'type':str[2].substring(5),
									'category':str[3].substring(9)						
				}
				
			}).success(function(data){
				window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/success.html','_self');

			});
		}	
	}
	
	
	
	
	
		
});
