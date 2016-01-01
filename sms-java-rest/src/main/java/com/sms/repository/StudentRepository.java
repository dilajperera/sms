package com.sms.repository;

import com.sms.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dperera
 */
@Repository("studentRepository")
public interface StudentRepository extends CrudRepository<Student, String>{
	
	//query ll be auto generated
	Student findByFirstName(String firstName);
    Student findById(String id);
}
