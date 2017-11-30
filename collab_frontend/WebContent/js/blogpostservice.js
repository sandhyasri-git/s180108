/**
 * 
 */

myApp.factory('blogPostService',function($http){
	var blogPostService={};
	var BASE_URL="http://localhost:8093/collab_backend"
	blogPostService.addBlog=function(blogPost){
		return $http.post(BASE_URL+"/addBlog",blogPost)
	}
	
	return blogPostService;
})
