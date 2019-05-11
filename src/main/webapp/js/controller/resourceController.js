app.controller('resController',function($scope,$http, $localStorage, $location,$window){
	console.log('Controller loaded');
	
	$scope.list = [];
	
	$scope.init = function(){
		$scope.getFileList("img");
		$scope.currDir = "img";
	}
	
	$scope.currDir = "";
	$scope.previousDir = "";
	
	$scope.getFileList = function(dir){
		if(dir != ""){
		$http.get("/listImageDirectory?dir="+dir).then(function(response) {
			$scope.list = response.data;
			if(dir != 'img'){
				$scope.previousDir = angular.copy($scope.currDir);
			}else{
				$scope.previousDir = "";
			}
			
			$scope.currDir = dir;
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });}
	}
	
	$scope.openFileInput = function(){
		$window.document.getElementById('imgInp').click();
	}
	
	$scope.uploadFile = function(files){
		console.log(files);
		var data = new FormData();
		
		  for (var i in files) {
              data.append("uploadedFile", files[i]);
          }
		  
		  data.append("data",$scope.currDir)
		  
		  $http.post("/uploadFile",data,  {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        }).then(function(response) {
				$scope.getFileList($scope.currDir);
				alert(response.data.msg);
			  }, function(response) {
			      $scope.content = "Something went wrong";
			  });
	}
	$scope.crateFolder= function(){
		var folderName = prompt("Please enter folder name.");
		$http.post("/createFolder",$scope.currDir+"/"+folderName).then(function(response) {
			$scope.getFileList($scope.currDir);
			alert(response.data.msg);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
		
	}
	
	$scope.delFolder = function(dir,fileName){
		var conf = prompt("Type file/folder name to delete?");
		if(conf == fileName){
		$http.post("/deleteFolder?dir="+dir).then(function(response) {
			$scope.getFileList($scope.currDir);
			alert(response.data.msg);
		  }, function(response) {
		      $scope.content = "Something went wrong";
		  });
		}
	}
	
	
	$scope.init();
	
});