myApp.controller('PanelListCtrl', function($scope) {
	$scope.checkSecretary = function(key) {
		return localStorage.getItem(key).trim() === "Secretary";
	}

	$scope.checkUser = function(key) {
		return localStorage.getItem(key).trim() === "User"
				|| localStorage.getItem(key).trim() === "PermanentUser";
	}
});
