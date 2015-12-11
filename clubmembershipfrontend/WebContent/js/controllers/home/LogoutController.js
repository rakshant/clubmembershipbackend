var app=angular.module("home");
app.controller('LogoutCtrl', function($scope,$http,url,$state) {
	$scope.home=function(){
		var check=localStorage.getItem('userId');
		if(check==null){
			return true;
		}
		else
			return false;
	}
	$scope.profile=function(){
		var check=localStorage.getItem('userType');
		if(check!=null){
			return true;
		}
		else
			return false
	}
	
	$scope.loginCheck = function() {
		$http({
			method : 'GET',
			url : url + 'logout' + "/" + localStorage.getItem('userId')			
		}).success(function(data) {			
			localStorage.removeItem('userId');
			localStorage.removeItem('userType')
			$state.go('home');			
		});
	}
});