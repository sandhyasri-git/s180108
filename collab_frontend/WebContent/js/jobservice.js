/**
 * JobService
 */

myApp.factory('JobService',function($http){
	var jobService={}
	
	jobService.saveJob=function(job){
	  return  $http.post("http://localhost:8093/collab_backend/savejob",job)
	}
	jobService.getAllJobs=function(){
		 return  $http.get("http://localhost:8093/collab_backend/getalljobs")
	}
	return jobService;
})