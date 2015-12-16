var app=angular.module("home");

app.controller('PermanentFacilitiesCtrl', function($scope,$http,url) {	
	
	
	 $scope.facilities = [
	                  {id:1,category:'indoor', type: 'tableTennis', price:1000},
	                  {id:2,category:'indoor', type: 'badminton', price:500},
	                  {id:3,category:'indoor', type: 'billiards', price:1500},
	                  {id:4,category:'indoor', type: 'healthClub', price:300},
	                  {id:5,category:'indoor', type: 'squash', price:2500}   ,
	                  {id:6,category:'outdoor', type: 'lawnTennis', price:1500},	                  
	                  {id:7,category:'outdoor', type: 'swimming', price:500},
	                  {id:8,category:'outdoor', type: 'cricket', price:3000} ,
	                  {id:9,category:'outdoor', type: 'playground', price:300},          
	                  {id:10,category:'leisure', type: 'cardRoom', price:500} ,         
	                  {id:11,category:'leisure', type: 'library', price:300}  ,        
	                  {id:12,category:'leisure', type: 'restaurantBar', price:1000},       
	                  {id:13,category:'leisure', type: 'banquetHall', price:50000},      
	                  {id:14,category:'leisure', type: 'conferenceHall', price:40000}
	               ];
	 
	 
	 $scope.isChecked = function(id){
	      var match = false;
	      for(var i=0 ; i < $scope.data.length; i++) {
	        if($scope.data[i].id == id){
	          match = true;
	        }
	      }
	      return match;
	  };
	  
	 
	    
	  $scope.data = [
	   	  ];

	  $scope.sync = function(bool, item){
	    if(bool){
	      // add item
	      $scope.data.push(item);
	    } else {
	      // remove item
	      for(var i=0 ; i < $scope.data.length; i++) {
	        if($scope.data[i].id == item.id){
	          $scope.data.splice(i,1);
	        }
	      }      
	    }
	  };

 $scope.check=function(data){
	 
		 localStorage.setItem('facility',JSON.stringify({'item': $scope.data}));
		 var totalPrice=0;
	 
	 	 for (var j = 0; j <  $scope.data.length; j++){
	 		 
	 		totalPrice=totalPrice+$scope.data[j].price; 

		 }
	 	 console.log("Price----->"+totalPrice);
	 
	window.open('http://localhost:8089/clubmembershipfrontend/paymentmodule/paymentModule.html?id='
					+localStorage.getItem('userId')+'&fee='+totalPrice+'&type=permanentFacility' , '_blank');
		
	 }

	
	  
});



