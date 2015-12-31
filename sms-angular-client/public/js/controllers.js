angular.module('controllers',['studentService']).
	controller('studentCtrl', ['$scope','studentFactory',function($scope,studentFactory){
		
        $scope.studentList = [];
        $scope.student = {};
        $scope.enableEditPanel = false;
        $scope.successMessage = false;
        getAllStudents();

        //to add/update student 
		$scope.addStudent = function(student){
			studentFactory.addStudent(student).then(function(student) {
				$scope.enableEditPanel = !$scope.enableEditPanel;
				$scope.student = {};
				$scope.successMessage = true;
            },
            function(error) {
                console.log('students details can not be saved'+error);
            });
		}
		
		//to get all details about students
		function getAllStudents(){
			studentFactory.getAllStudents().then(function(students) {
				$scope.studentList = students;
            },
            function(error) {
                console.log('students details can not be loaded '+error);
            });
		};	

		//to bind update student with scope
		$scope.renderEditPanel = function(student){
			$scope.student = student;
			$scope.enableEditPanel = !$scope.enableEditPanel;
			$scope.successMessage = false;
		}

        $scope.deleteStudent = function (student) {
            studentFactory.deleteStudent(student).then(function (result) {
                    if(result.success){
                        $scope.studentList.pop(student);
                        $scope.student = {};
                        $scope.enableEditPanel = false;
                    }
                },
                function (error) {
                    console.log('students details can not be saved'+error);
                });

        }
		
}]);