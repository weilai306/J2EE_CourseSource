package jee.ejb.manytomany2.ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jluee.entity.Customer;
import jluee.entity.Goods;
import jluee.entity.Orders;

/**
 * Session Bean implementation class ManyToMany2Ejb
 */
@Stateless
@LocalBean
public class ManyToMany2Ejb implements ManyToManyEjbLocal {
	@PersistenceContext(unitName = "MyPractice")
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public ManyToMany2Ejb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void appendOrder(Orders order) {
		// TODO Auto-generated method stub
		manager.persist(order);
	}

	@Override
	public Orders merge(Orders order) {
		// TODO Auto-generated method stub
		return manager.merge(order);
	}
	@Override
	public int updateOrder(Orders order) {
		// TODO Auto-generated method stub
		String jpql = "UPDATE Orders o SET o.name=?1 ,o.goods=?2,o.customer=?3 WHERE o.id=?4";
		Query query = manager.createQuery(jpql);
		query.setParameter(1, order.getName());
		query.setParameter(2, order.getGoods());
		query.setParameter(3, order.getCustomer());
		query.setParameter(4, order.getId());
		// 返回受影响的行数
		return query.executeUpdate();
	}

	@Override
	public Orders findOrder(int id) {
		// TODO Auto-generated method stub
		Orders order = null;
		try {
			String jpql = "SELECT o from Orders o where o.id=?1";
			Query query = manager.createQuery(jpql);
			query.setParameter(1, id);
			order = (Orders) query.getSingleResult();
		} catch (Exception e) {
		}
		return order;
	}

	@Override
	public Customer findCustomer(int id) {
		// TODO Auto-generated method stub
		Customer customer = null;
		try {
			String jpql = "SELECT c from Customer c where c.id=?1";
			Query query = manager.createQuery(jpql);
			query.setParameter(1, id);
			customer = (Customer) query.getSingleResult();
		} catch (Exception e) {			
		}
		return customer;
	}

	@Override
	public Goods findGoods(int id) {
		// TODO Auto-generated method stub
		Goods goods = null;
		try {
			String jpql = "SELECT g from Goods g where g.id=?1";
			Query query = manager.createQuery(jpql);
			query.setParameter(1, id);
			goods = (Goods) query.getSingleResult();
		} catch (Exception e) {
		}
		return goods;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		Customer customer = (Customer) manager.find(Customer.class, id);
		if (customer != null) {
			for(Orders orders:customer.getOrders()) {
				deleteOrder(orders.getId());
			}
		}
	}

	@Override
	public void deleteGoods(int id) {
		// TODO Auto-generated method stub
		Goods goods = (Goods) manager.find(Goods.class, id);
		if (goods != null) {
			for(Orders orders:goods.getOrders()) {
				deleteOrder(orders.getId());
			}
		}
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		Orders order = (Orders) manager.find(Orders.class, id);
		if (order != null) {
			manager.createQuery("DELETE FROM Orders o WHERE o.id = ?1").setParameter(1, id).executeUpdate();
			manager.createQuery("DELETE FROM Customer c WHERE c.id = ?1").setParameter(1, order.getCustomer().getId())
					.executeUpdate();
			manager.createQuery("DELETE FROM Goods g WHERE g.id = ?1").setParameter(1, order.getGoods().getId())
					.executeUpdate();
		}
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		manager.flush();
	}

	@Override
	public boolean contains(Orders order) {
		// TODO Auto-generated method stub
		return manager.contains(order);
	}

	@Override
	public Orders find(Orders orders) {
		// TODO Auto-generated method stub
		return (Orders)manager.find(Orders.class, orders.getId());
	}

	
}
