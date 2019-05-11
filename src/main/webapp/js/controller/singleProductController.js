var app = angular.module('myApp', []);
app.controller('singleProdController',function($scope,$http, $location,$window,$sce){
	console.log('Controller loaded');
	
	$scope.products = [];
	$scope.product = {};
	 $scope.myInterval = 2000;
	 $scope.slides = [];
	
	$scope.init = function(){
		var url = new URL($location.absUrl());
		var id = url.searchParams.get("pid");
		console.log(id);
		$scope.product.id = id;
		$scope.getProducts();
		$scope.getProduct();
		
	
		console.log($scope.product)
	}
	
	
	
	$scope.getSlides = function(){
		angular.forEach($scope.product.productImages, function(image){
			$scope.slides.push(
					{
						"img" : image
					});
		});
		
	}
	
	$scope.getProducts = function(){
		$http.get("/getAllProducts").then(function(response) {
			$scope.products = response.data;
			
			
			//console.log($scope.products)
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.getProduct = function(){
		
		$http.get("/getProduct/"+$scope.product.id).then(function(response) {
			$scope.product = response.data;
			$scope.product.longDescription = $sce.trustAsHtml($scope.product.longDescription);
			$scope.product.shortDescription = $sce.trustAsHtml($scope.product.shortDescription);
			$scope.getSlides();
			$scope.getDimensions();
			$scope.getCatalouge();
			$scope.getLegends();
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	
	$scope.getCatalouge = function(){
		$http.get("/getCatalouge/"+$scope.product.id).then(function(response) {
			$scope.product.catalougeItem = response.data;
			//console.log(response.data);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.getLegends = function(){
		$http.get("/getLegends/"+$scope.product.id).then(function(response) {
			$scope.product.legends = response.data;
			//console.log(response.data);
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