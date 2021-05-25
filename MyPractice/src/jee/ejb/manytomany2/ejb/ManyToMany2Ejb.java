package jee.ejb.manytomany2.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jluee.entity.Customer;
import jluee.entity.Goods;
import jluee.entity.Order;

/**
 * Session Bean implementation class ManyToMany2Ejb
 */
@Stateless
@LocalBean
public class ManyToMany2Ejb implements ManyToManyEjbLocal{
	@PersistenceContext(unitName = "MyPractice2")
	private EntityManager manager;
    /**
     * Default constructor. 
     */
    public ManyToMany2Ejb() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void appendOrder(Order order) {
		// TODO Auto-generated method stub
		manager.persist(order);
	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub
		String jpql = "DELETE FROM Order WHERE id = ?1";
	    Query query = manager.createQuery(jpql);
	    query.setParameter(1, id);
	    query.executeUpdate();
	}

	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		String jpql = "UPDATE Order SET name=?1 ,goods=2?,customer=3? WHERE id=?4";
	    Query query = manager.createQuery(jpql);
	    query.setParameter(1, order.getName());
	    query.setParameter(2, order.getGoods());
	    query.setParameter(3, order.getCustomer());
	    query.setParameter(4, order.getId());
	    // 返回受影响的行数
	   return query.executeUpdate();
	}

	@Override
	public Order findOrder(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Goods findGoods(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGoods(int id) {
		// TODO Auto-generated method stub
		
	}
    
}
