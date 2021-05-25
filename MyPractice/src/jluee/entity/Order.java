package jluee.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
	private Customer customer;
	
	@ManyToOne(cascade = { CascadeType.ALL }, optional = false)
	@JoinColumn(name = "GOODS_ID", referencedColumnName = "ID")
	private Goods goods;
	
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Order() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	

	

}
