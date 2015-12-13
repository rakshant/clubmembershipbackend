var app=angular.module("home");
app.controller('CarouselDemoCtrl', function($scope,$http) {
	$scope.myInterval = 2000;
	$scope.slides = [ {
		image : 'images\\ch1.jpg'
	}, {
		image : 'images\\ch2.jpg'
	}, {
		image : 'images\\ch3.jpg'
	}, ];
});


//for(var i=0;i<100000;i++){
//setTimeout(function(){ $http.get("http://google.com") }, 3000);		
//}