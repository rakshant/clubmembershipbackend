myApp.controller('viewCtrl', function($scope, $http, $state) {

	$http.get('http://localhost:8080/viewdetails').success(function(result) {
		console.log('-->' + result);
		$scope.personalDetails = result;
		console.log('-->' + result[0].firstName);
	});

});