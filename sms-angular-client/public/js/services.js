angular.
module('studentService',[]).
factory('studentFactory', ['$http','$q', function($http, $q){

	//The service class is responsible to manage CRUD
	//operations with the REST api.

    var baseUrl = 'http://localhost:8080/sms/students';

        function addStudent(student) {
        var deferred = $q.defer();
		$http({
			method: 'POST',
		    url: baseUrl,
		    data: student
		 }).success(function(data){
			deferred.resolve(data);   
		 }).error(function(error){
			deferred.reject(error);
		 });
		
		 return deferred.promise;
	};
	
	function getAllStudents() {
		var deferred = $q.defer();
		$http({
			method: 'GET',
		    url: baseUrl
		 }).success(function(data){
			 deferred.resolve(data);  
		 }).error(function(error){
			deferred.reject(error); 
		 });
		 return deferred.promise;
	};
	
	function deleteStudent(student) {
		var deferred = $q.defer();
		$http({
			method: 'DELETE',
		    url: baseUrl+'/'+student.id
		 }).success(function(data){
			 deferred.resolve(data);
		 }).error(function(error){
			deferred.reject(error);
		 });
		 return deferred.promise;
	};
	
	
	return ({
		addStudent: addStudent,
		getAllStudents: getAllStudents,
		deleteStudent: deleteStudent
	});

}]);