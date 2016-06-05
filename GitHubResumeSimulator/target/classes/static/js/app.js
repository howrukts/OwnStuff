'use strict';
var app = angular.module('github_app', []);
app.controller('github_ctrl', github_ctrl);

function github_ctrl($scope, $http) {
	$scope.display = function(git) {
    	
    	var username = $scope.git.name;
    	
    	$scope.giturl = "getDetails";
    	$scope.reposurl = "getRepos";
    	
    	angular.isUndefinedOrNull = function(lang) {
            return angular.isUndefined(lang) || lang === null 
        }
 
    	var request = $http({
    	    url: 'getDetails', 
    	    method: "GET",
    	    params: {username: username}
    	 });
    	request.success(function(data, status, headers, config) {
    		$scope.details = data;
    		
    		var reposrequest = $http({
        	    url: 'getRepos', 
        	    method: "GET",
        	    params: {username: username}
        	 });
    		reposrequest.success(function(repodata, status, headers, config) {
    			$scope.success = true;
            	$scope.repos = repodata;
            	$scope.langs = [];
            	
            	angular.forEach($scope.repos,function(item) {
            		if(!angular.isUndefinedOrNull(item.language) && $scope.langs.indexOf(item.language) == -1) {
            			$scope.langs.push(item.language);
            		}
            	});
    		})
    		
    		reposrequest.error(function(repodata, status, headers, config) {
    			$scope.success = false;
                alert("Error accessing repository API");
    		})
        })
    	
    	request.error(function(data, status, headers, config) {
    		$scope.success = false;
            alert("Invalid username");
        })       
   }
}