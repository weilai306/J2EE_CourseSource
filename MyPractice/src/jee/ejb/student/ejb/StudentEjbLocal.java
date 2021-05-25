package jee.ejb.student.ejb;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import jluee.entity.Student;

@Local
public interface StudentEjbLocal {
	public void createStudent();

	public Student findStudentById(Integer studentId);

	public Student updateStudent(Student student);

	public void deleteStudent(Integer studentld);
	
	public void detach(Student student);
}
