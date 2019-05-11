app.controller('loginController',function($scope,$http, $localStorage, $location,$window){
	console.log('Controller loaded');
	if($localStorage.keepLoggedIn == true){
		console.log('keep logged in');
		$window.location.href = "/admin/index.html";
	}
	$scope.login = function(user){
		console.log(user);
		$http.post("/login",user).then(function(response) {
		      $scope.user = response.data;
		      if($scope.user.loginSuccess){
		    	  if($scope.user.rememberMe){
		    		  $localStorage.keepLoggedIn = true;
		    	  }
		      }
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
});