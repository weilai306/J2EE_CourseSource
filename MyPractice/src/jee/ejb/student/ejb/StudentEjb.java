package jee.ejb.student.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import jluee.entity.*;

/**
 * Session Bean implementation class StudentEjb
 */
@Stateless
@LocalBean
public class StudentEjb implements StudentEjbLocal{
	@PersistenceContext(unitName = "MyPractice")
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public StudentEjb() {
		// TODO Auto-generated constructor stub
	}

	public void createStudent() {
		Student p = new Student();
		p.setName("wangwu6");
		p.setGender("male");
		p.setMajor("computer");
		Address ad = new Address();
		ad.setDetail("����ʡ������ǰ�����2699��");
		ad.setProvince("����ʡ");
		ad.setCity("������");
		ad.setZip("130012");
		p.setAddress(ad);
		
		manager.persist(p);
	}

	public Student findStudentById(Integer studentId) {
		Student student = manager.find(Student.class, studentId);
		return student;
	}

	public Student updateStudent(Student student) {
		Student s = manager.merge(student);
		return s;
	}

	public void deleteStudent(Integer studentld) {
		Student student = manager.find(Student.class, studentld);
		manager.remove(student);
	}
	
	public void detach(Student student) {
		manager.detach(student);
	}
}
