myApp.controller('CarouselDemoCtrl', function($scope) {
    $scope.myInterval = 2000;
    $scope.slides = [{
        image: 'images\\ch1.jpg'
    }, {
        image: 'images\\ch2.jpg'
    }, {
        image: 'images\\ch3.jpg'
    }, ];
});


myApp.controller('TabsDemoCtrl', function($scope) {

});



myApp.controller('DatepickerDemoCtrl', function ($scope) {


  $scope.open = function($event) {
    $scope.status.opened = true;
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];

  $scope.status = {
    opened: false
  };
});




myApp.controller('RegisterationCtrl',function($scope,$http) {
	
	$scope.submit=function(){
					$http({
				    method: 'POST',
				    url: 'http:\\localhost:8080\register',
				    data: {	'email':$scope.email,
				    		'firstName':$scope.firstName,
				    		'lastName':$scope.lastName,
				    		'mobileNo':$scope.mobileNo,
				    		'occupation':$scope.occupation,
				    		'date':$scope.date
				    	}
				    
				});	
	}
	
	});