app.controller('productController',function($scope,$http, $localStorage, $location,$window){
	console.log('Controller loaded');
	
	$scope.products = [];
	$scope.product = {};
	$scope.viewProduct = {};
	$scope.legendHeader = "";
	$scope.legend = {};
	$scope.btnFlag = false;
	$scope.btnFlag1 = false;
	
	$scope.catItem = {};
	
	$scope.init = function(){
		$scope.getProducts();
		$scope.resetProduct();
	}
	
	$scope.getProducts = function(){
		$http.get("/getAllProducts").then(function(response) {
			$scope.products = response.data;
			console.log($scope.products)
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.openProductForEdit = function(p){
		$scope.viewProduct = angular.copy(p);
		$scope.selectedImage = p.catalougeImage;
		$scope.getLegends(p.id);
		$scope.getCatalouge(p.id);
		$scope.getDimensions();
		console.log($scope.viewProduct)
	}
	
	$scope.cancelEdit = function(){
		$scope.viewProduct  = {}
	}
	
	$scope.resetProduct = function(){
		$scope.product = {};
		$scope.product.availability = "In Stock";
		$scope.product.innerDia = true;
		$scope.product.outerDia = true;
		$scope.product.productImages = [];
		$scope.selectedImage = "";
	}
	
	$scope.addProduct = function(product){
		product.catalougeImage = $scope.selectedImage;
		console.log(product);
		$http.post("/addProduct",product).then(function(response) {
			$scope.resetProduct();
			console.log(response.data);
			$scope.getProducts();
			//alert('Product added successfully');
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.selectImage = function(img){
		console.log(img)
		$scope.product.productImages.push(img);
		$scope.product.defaultImage = img;
		console.log($scope.product.productImages);
	}
	
	$scope.selectImageForView = function(img){
		console.log(img)
		$scope.viewProduct.productImages.push(img);
		$scope.viewProduct.defaultImage = img;
		console.log($scope.viewProduct.productImages);
	}
	
	$scope.removeImage = function(index){
		$scope.product.defaultImage = $scope.product.productImages[0]
		$scope.product.productImages.splice(index,1);
	}
	
	$scope.removeImageView = function(index){
		$scope.viewProduct.defaultImage = $scope.viewProduct.productImages[0]
		$scope.viewProduct.productImages.splice(index,1);
	}
	
	$scope.addHeader = function(){
		$scope.viewProduct.legendHeader.push($scope.legendHeader);
		$scope.addProduct($scope.viewProduct);
		$scope.legendHeader = "";
	}
	
	$scope.deleteProduct = function(product){
		var conf = confirm("Are you sure you want to delete product - "+product.productName+"?");
		if(conf){
		$http.post("/deleteProduct",product.id).then(function(res){
			$scope.getProducts();
		})
		}
	}
	
	$scope.getLegends = function(id){
		$http.get("/getLegends/"+id).then(function(response) {
			$scope.viewProduct.legends = response.data;
			console.log(response.data);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.addLegend = function(legend,header){
		legend.productId = $scope.viewProduct.id;
		legend.legendHeader = header;
		$http.post("/addLegend",legend).then(function(response) {
			console.log(response.data);
			$scope.getLegends(legend.productId);
			$scope.legend = {};
			$scope.btnFlag = false;
			alert('Legend added successfully');
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.saveCatalouge = function(cat){
		cat.productId = $scope.viewProduct.id;
		$http.post("/addCatalouge",cat).then(function(response) {
			console.log(response.data);
			$scope.getCatalouge(cat.productId);
			$scope.catItem = {};
			$scope.btnFlag1 = false;
			alert('Catalouge added successfully');
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.selectedImage = "";
	
	$scope.selectSingleImage = function(img){
		$scope.selectedImage = img;
	}
	
	$scope.removeSelectedImage = function(){
		$scope.selectedImage = "";
	}
	
	$scope.getCatalouge = function(id){
		$http.get("/getCatalouge/"+id).then(function(response) {
			$scope.viewProduct.catalougeItem = response.data;
			console.log(response.data);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
	}
	
	$scope.deleteCatalouge  = function(l){
		var conf = confirm("Are you sure you want to delete Catalouge  ?");
		if(conf){
		$http.post("/deleteCatalouge/"+l.id).then(function(res){
			$scope.getCatalouge(l.productId);
		})
		}
	}
	
	$scope.editCatalouge = function(l){
		$scope.catItem = angular.copy(l);
		$scope.btnFlag1 = true;
	}
	
	
	$scope.cancelEditCatalouge = function(){
		$scope.catItem = {};
		$scope.btnFlag1 = false;
	}
	$scope.editLegend = function(l){
		$scope.legend = angular.copy(l);
		$scope.btnFlag = true;
	}
	
	$scope.cancelEditLegend = function(){
		$scope.legend = {};
		$scope.btnFlag = false;
	}
	
	$scope.deleteLegend = function(l){
		var conf = confirm("Are you sure you want to delete Legend ?");
		if(conf){
		$http.post("/deleteLegend/"+l.id).then(function(res){
			$scope.getLegends(l.productId);
		})
		}
	}
	
	$scope.catalougeDimension = [];
	
	$scope.getDimensions = function(){
		$scope.catalougeDimension = [];
		if($scope.viewProduct.innerDia){
			$scope.catalougeDimension.push("d");
		}
		if($scope.viewProduct.outerDia){
			$scope.catalougeDimension.push("D");
		}
		if($scope.viewProduct.width){
			$scope.catalougeDimension.push("B");
		}
		if($scope.viewProduct.smallThickness){
			$scope.catalougeDimension.push("C");
		}
		if($scope.viewProduct.thickness){
			$scope.catalougeDimension.push("T");
		}
		if($scope.viewProduct.centreHeight){
			$scope.catalougeDimension.push("H");
		}
		if($scope.viewProduct.outerLength){
			$scope.catalougeDimension.push("L");
		}
		if($scope.viewProduct.innerLength){
			$scope.catalougeDimension.push("J");
		}
		if($scope.viewProduct.flank){
			$scope.catalougeDimension.push("F");
		}
	}
	
	$scope.init();
});