package jee.ejb.manytomany2.ejb;

import jluee.entity.Customer;
import jluee.entity.Goods;
import jluee.entity.Order;

public interface ManyToManyEjbLocal {
	public void appendOrder(Order order);
	public void deleteOrder(int id);
	public int updateOrder(Order order);
	public Order findOrder(int id);
	public Customer findCustomer(int id);
	public Goods findGoods(int id);
	public void deleteCustomer(int id);
	public void deleteGoods(int id);
}
