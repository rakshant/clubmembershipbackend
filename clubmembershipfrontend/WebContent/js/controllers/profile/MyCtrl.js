var myApp = angular.module("home");

	myApp.controller('MyCtrl', function($scope) {

	    $scope.relations = [
	        { id: 1, type: 'Spouse'},
	        { id: 2, type: 'Son'},
	        { id: 3, type: 'Daughter'}
	    ];
	    
	    $scope.selected = $scope.relations[0];

	 /*   $scope.lov_type = [
	        {'lookupCode': 'Spouse', 'description': 'Spouse'},
	        {'lookupCode': 'Son', 'description': 'Son'},
	        {'lookupCode': 'Daughter', 'description': 'Daughter'}
	        
	    ];*/
	});



