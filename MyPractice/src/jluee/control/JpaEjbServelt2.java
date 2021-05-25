package jluee.control;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jluee.entity.Student;

/**
 * Servlet implementation class JpaEjbServelt2
 */
@WebServlet("/JpaEjbServelt2")
public class JpaEjbServelt2 extends HttpServlet {
	@EJB
	jee.ejb.student.ejb.StudentEjbLocal testejb;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JpaEjbServelt2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 增
		testejb.createStudent();
		// 查
		Student stu = testejb.findStudentById(38);
		if (stu != null) {
			System.out.println(stu.getId() + " " + stu.getName());
		} else {
			System.out.println("null find");
		}
		// 改
		if (stu != null) {
			testejb.detach(stu);
			stu.setName("weilai");
			stu = testejb.updateStudent(stu);
			if (stu != null) {
				System.out.println(stu.getId() + " " + stu.getName());
			} else {
				System.out.println("null merge");
			}
		}
		// 刪
		if (stu != null) {
			testejb.deleteStudent(stu.getId());
		} else {
			System.out.println("null delete");
		}
		// 查
		if (stu != null) {
			stu = testejb.findStudentById(stu.getId());
			if (stu == null) {
				System.out.println("stu is null");
			} else {
				System.out.println("delete fail");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
