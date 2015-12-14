var app = angular.module("profile");

app.controller('PanelListCtrl', function($scope,$timeout) {
	$scope.checkSecretary = function(key) {
		return localStorage.getItem(key).trim() === "Secretary";
	}
	$scope.checkUser = function(key) {
		return localStorage.getItem(key).trim() === "User"
				|| localStorage.getItem(key).trim() === "PermanentUser";
	}
	
	for(i=0;i<100;i++){
		$timeout(callAtTimeout, 3000);
	}
});


function callAtTimeout() {
	console.log("Timeout occurred");
}
