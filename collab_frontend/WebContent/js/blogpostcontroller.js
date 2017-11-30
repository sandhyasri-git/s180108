/**
 * BlogPostController
 */
myApp.controller('blogPostController',function(BlogPostService,$scope,$location){
	$scope.addBlog=function(){
		blogPostService.addBlog($scope.blogPost).then(function(response){
			console.log(response.status)
			alert('BlogPost added successfully.. It is waiting for approval')
			$location.path('/blog1')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$location.path('/blog1')
		})
	}
})

