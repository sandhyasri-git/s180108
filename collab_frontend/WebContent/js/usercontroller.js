/**
 * userController
 */
myApp.controller('userController',function(UserService,$scope,$rootScope,$location,$cookieStore){
	$scope.user={}
	$scope.registerUser=function(){
			UserService.registerUser($scope.user).then(
					function(response){
				$scope.message="Registered Successfully.. Pls. login again"
					$location.path('/login')
			},function(response){
				console.log(response.status)
				console.log(response.data)
				$scope.error=response.data
				$location.path('/registrationform')
				
			})
	}
	$scope.validateUser=function(){
		UserService.validateUser($scope.user).then(function(response){
			console.log(response.data)
			$rootScope.currentUser=response.data
			$cookieStore.put("currentUser",response.data)
			$location.path('/home')
		},function(response){
			$scope.error=response.data
			console.log(response.status)
			$location.path('/login')
		})
	}
	$scope.updateUser=function(){
		UserService.updateUser($rootScope.currentUser).then(function(response){
			alert("updated successfully")
			$location.path('/home')
		},function(response){
			console.log(response.data)
			/*
			 * For unauthorized access, 401 -> redirect the user to login page
			 * For Exception , 500  -> redirect the user to updateprofile page
			 */
			if(response.status==401)
				$location.path('/login')
			$location.path('/updateprofile')
		})
	}
	$rootScope.logout=function(){
		UserService.logout().then(function(response){
			$rootScope.logoutSuccess="Loggedout successfully..."
				delete $rootScope.currentUser
				$cookieStore.remove("currentUser")
				$location.path('/login')
				
		},function(response){
			$scope.error=response.data
			$location.path('/login')
		})
	}
})