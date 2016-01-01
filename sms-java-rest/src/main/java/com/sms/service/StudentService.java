package com.sms.service;

import com.sms.domain.Student;

import java.util.List;

/**
 * @author dperera
 */
public interface StudentService {
	
	List<Student> getAllStudents();
	Student saveStudent(Student student);
	void deleteStudent(String id);
}
