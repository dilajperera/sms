package com.sms.connector;

import com.sms.domain.Student;
import com.sms.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * @author dperera
 */
@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	private static final Logger logger = LogManager.getLogger(StudentController.class);

    @RequestMapping(method=RequestMethod.POST)
	public Student addStudent(@RequestBody @Valid Student student, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			logger.info("Validation fail - student {name : {} {} } ll be added",student.getFirstName(),student.getLastName());
		}
		logger.info("A new student {name : {} {} } ll be added",student.getFirstName(),student.getLastName()); 
		return studentService.saveStudent(student);
	}

	/**
	 * This methods gives details of students
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public List<Student> getAllStudents() {
		logger.info("Students details ll be loaded..."); 
		return studentService.getAllStudents();
    }

    /**
     *
     * @param studentId
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteStudent1(@PathVariable("id") String studentId) {
        studentService.deleteStudent(studentId);
        return "{ \"success\" : true }";
    }
	

}
