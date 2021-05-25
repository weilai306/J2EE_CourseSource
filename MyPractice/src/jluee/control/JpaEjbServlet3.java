package jluee.control;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jluee.entity.Customer;
import jluee.entity.Goods;
import jluee.entity.Orders;

/**
 * Servlet implementation class JpaEjbServlet3
 */
@WebServlet("/JpaEjbServlet3")
public class JpaEjbServlet3 extends HttpServlet {
	@EJB
	jee.ejb.manytomany2.ejb.ManyToManyEjbLocal ejb;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JpaEjbServlet3() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Customer c1 = new Customer();
		c1.setName("customer1");

		Customer c2 = new Customer();
		c2.setName("customer2");

		Customer c3 = new Customer();
		c3.setName("customer3");

		Goods g1 = new Goods();
		g1.setPrice(1);

		Goods g2 = new Goods();
		g2.setPrice(2);

		Goods g3 = new Goods();
		g3.setPrice(3);

		Orders o1 = new Orders();
		o1.setName("order1");
		o1.setCustomer(c1);
		o1.setGoods(g1);

		Orders o2 = new Orders();
		o2.setName("order2");
		o2.setCustomer(c2);
		o2.setGoods(g2);

		Orders o3 = new Orders();
		o3.setName("order3");
		o3.setCustomer(c3);
		o3.setGoods(g3);

		// 增加
		ejb.appendOrder(o1);
		ejb.appendOrder(o2);
		ejb.appendOrder(o3);
		response.getWriter().append("persist Order<br/>");

		// 删除
		ejb.deleteGoods(g1.getId());
		ejb.deleteCustomer(c2.getId());
		ejb.deleteOrder(o3.getId());
		response.getWriter().append(ejb.findCustomer(c1.getId()) == null ? "null<br/>" : "not null<br/>");
		response.getWriter().append(ejb.findGoods(g2.getId()) == null ? "null<br/>" : "not null<br/>");
		response.getWriter().append(ejb.findOrder(o3.getId()) == null ? "null<br/>" : "not null<br/>");

		// 修改
		o1 = ejb.merge(o1);
		o2 = ejb.merge(o2);
		o3 = ejb.merge(o3);
		response.getWriter().append("merge Order<br/>");
		o1.setName("newName");
		response.getWriter().append(Integer.toString(ejb.updateOrder(o1))+"<br/>");
		
		//查询
		o1 = (Orders) ejb.findOrder(188);
		if (o1 != null) {
			response.getWriter().append(o1.getName() + "<br/>");
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
