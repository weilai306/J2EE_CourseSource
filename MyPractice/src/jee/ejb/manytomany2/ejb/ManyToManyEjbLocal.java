package jee.ejb.manytomany2.ejb;

import javax.ejb.Local;

import jluee.entity.Customer;
import jluee.entity.Goods;
import jluee.entity.Orders;

@Local
public interface ManyToManyEjbLocal {
	public void appendOrder(Orders order);
	public void deleteOrder(int id);
	public int updateOrder(Orders order);
	public Orders findOrder(int id);
	public Customer findCustomer(int id);
	public Goods findGoods(int id);
	public void deleteCustomer(int id);
	public void deleteGoods(int id);
	public void flush();
	public Orders merge(Orders order);
	public boolean contains(Orders order);
	public Orders find(Orders orders);
}
