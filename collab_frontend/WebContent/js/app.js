/**
 * 
 */
var myApp=angular.module("myApp",['ngRoute' ,'ngCookies'])
myApp.config(function($routeProvider){
	$routeProvider
	.when('/home',{
		templateUrl:'views/home.html'
	})
	.when('/aboutus',{
		templateUrl:'views/aboutus.html'
	})
	
	.when('/Welcome',{
		templateUrl:'views/Welcome.html'
	})
	.when('/register', {
		templateUrl:'views/registrationform.html',
	
		controller: 'userController',
		
	})
	.when('/login',{
		controller:'userController',
		templateUrl:'views/login.html'
	})
	.when('/updateprofile',{
		templateUrl:'views/updateprofile.html',
		controller:'userController'
	})
	.when('/savejob',{
		templateUrl:'views/jobform.html',
		controller:'JobController'
	})
	.when('/getalljobs',{
		templateUrl:'views/jobtitles.html',
		controller:'JobController'
	})
	.when('/blog',
			{
		templateUrl:'views/blog1.html',
		controller:'blogpostcontroller'
		
	})
	.when('/allblogs',
			{
		templateUrl:'views/allblogs.html',
		controller:'allblogsController'
		
	})
.when('/adminblog',
			{
		templateUrl:'views/adminblog.html',
		controller:'adminBlogController'
		
	})

	.otherwise({
		templateUrl:'views/home.html'
	})
	
})
myApp.run(function($rootScope,$cookieStore){
		if($rootScope.currentUser==undefined)
				$rootScope.currentUser=$cookieStore.get("currentUser")
})