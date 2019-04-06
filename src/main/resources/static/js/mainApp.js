var app = angular.module('chatApp',[]);
app.controller('ctrl',function($scope,$http){
	$http.get('http://localhost:8080/home/friends').
		then(function(response) {
			$scope.friends = response.data;
			alert($scope.friends);
		}); 	
});
