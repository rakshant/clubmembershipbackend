myApp.controller('CarouselDemoCtrl', function($scope) {
	$scope.myInterval = 2000;
	$scope.slides = [ {
		image : 'images\\ch1.jpg'
	}, {
		image : 'images\\ch2.jpg'
	}, {
		image : 'images\\ch3.jpg'
	}, ];
});