app.controller('indexController',function($scope,$http, $localStorage, $location,$window){
	console.log('Controller loaded');
	
	$scope.products = [];
	$scope.product = {};
	$scope.enq = {};
	
	$scope.init = function(){
		$scope.getProducts();
	}
	
	$scope.message = {};
	
	$scope.sendEnquiry = function(){
		$scope.enq.read = false;
		$http.post("/sendEnquiry",$scope.enq).then(function(response) {
		    $scope.enq = {};  
			alert('Enquiry Sent Successfully');
		      
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.getProducts = function(){
		$http.get("/getAllProducts").then(function(response) {
			$scope.products = response.data;
			console.log($scope.products)
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.viewCatalouge = function(p){
		$scope.product = p;
		$scope.getCatalouge(p.id);
		$scope.getDimensions();
	}
	
	$scope.getCatalouge = function(id){
		$http.get("/getCatalouge/"+id).then(function(response) {
			$scope.product.catalougeItem = response.data;
			console.log(response.data);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.catalougeDimension = [];
	
	$scope.getDimensions = function(){
		$scope.catalougeDimension = [];
		if($scope.product.innerDia){
			$scope.catalougeDimension.push("d");
		}
		if($scope.product.outerDia){
			$scope.catalougeDimension.push("D");
		}
		if($scope.product.width){
			$scope.catalougeDimension.push("B");
		}
		if($scope.product.smallThickness){
			$scope.catalougeDimension.push("C");
		}
		if($scope.product.thickness){
			$scope.catalougeDimension.push("T");
		}
		if($scope.product.centreHeight){
			$scope.catalougeDimension.push("H");
		}
		if($scope.product.outerLength){
			$scope.catalougeDimension.push("L");
		}
		if($scope.product.innerLength){
			$scope.catalougeDimension.push("J");
		}
		if($scope.product.flank){
			$scope.catalougeDimension.push("F");
		}
	}
	
	
	
	$scope.init();
});