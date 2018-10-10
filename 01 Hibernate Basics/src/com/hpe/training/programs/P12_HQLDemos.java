package com.hpe.training.programs;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hpe.training.entity.Brand;
import com.hpe.training.entity.Customer;
import com.hpe.training.entity.Order;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P12_HQLDemos {

	private static Session session;
	
	public static void main(String[] args) {
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();
			
			// printAllBrands();
			// printProductsByPriceRange(40.0, 50.0);
			// printProductsByPage(3); // 3 --> pageNum
			// printProductNames(); // only names, not actual products
			// printProductNamesAndPrices();
			// printProductsByBrand("Zespri"); // Zespri --> brand name
			// printBrandwiseProductCount();
			// printOrderDetails(2); // 2 --> order_id
			// printCustomerDetailsWhoPlacedOrderMoreThan(150); // 150 -> order total
			updateProductPriceBy(1); // 1--> increment amount
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

	static void updateProductPriceBy(double incrAmount) {
		String hql = "update Product set unitPrice=unitPrice+:INCR_AMT";
		Query qry = session.createQuery(hql);
		qry.setParameter("INCR_AMT", incrAmount);
		int rc = qry.executeUpdate();
		session.beginTransaction().commit();
		System.out.println(rc + " records updated!");
	}

	static void printCustomerDetailsWhoPlacedOrderMoreThan(double orderTotal) {
		String sql = "select c.id, c.name, c.email, c.password, c.phone,\n" + 
				"c.address, c.city, c.state, c.country, o.id, o.order_date, o.status, o.customer_id\n" + 
				"from customers c \n" + 
				"join orders o on c.id = o.customer_id \n" + 
				"join line_items li on o.id = li.order_id\n" + 
				"group by c.id, c.name, c.email, c.password, c.phone,\n" + 
				"c.address, c.city, c.state, c.country, o.id, o.order_date, o.status, o.customer_id\n" + 
				"having sum(li.quantity*li.unit_price)  >= :ORDER_TOTAL";
		
		SQLQuery qry = session.createSQLQuery(sql);
		qry.addEntity(Customer.class);
		qry.addEntity(Order.class);
		
		qry.setParameter("ORDER_TOTAL", orderTotal);
		List<Object[]> customers = qry.list();
		for(Object[] arr: customers) {
			Customer c = (Customer) arr[0];
			// Order o = (Order) arr[1];
			System.out.println(c.getName() + " --> " + c.getEmail());
		}
	}

	static void printOrderDetails(int orderId) {
		String sql = "select c.name, o.order_date, o.status, sum(li.quantity*li.unit_price) order_total\n" + 
				"from customers c \n" + 
				"join orders o on c.id = o.customer_id \n" + 
				"join line_items li on o.id = li.order_id\n" + 
				"where o.id = :ORDER_ID \n" + 
				"group by c.name, o.order_date, o.status";
		
		Query qry = session.createSQLQuery(sql);
		qry.setParameter("ORDER_ID", orderId);
		Object[] data = (Object[]) qry.uniqueResult();

		if (data != null) {
			System.out.println("Customer     = " + data[0]);
			System.out.println("Order date   = " + data[1]);
			System.out.println("Order status = " + data[2]);
			System.out.println("Order amount = " + data[3]);
		}
		else {
			System.out.println("No data found for order id " + orderId);
		}
	}

	static void printBrandwiseProductCount() {
		
		String hql = "select p.brand.name, count(p) from Product p"
				+ " group by p.brand.name having count(p)>15";
		
		Query qry = session.createQuery(hql);
		
		List<Object[]> list = qry.list();
		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1]);
		}		
	}

	static void printProductsByBrand(String brandName) {
		// product.brand --> many-to-one association
		// String hql = "from Product where brand.name = :BRAND_NAME";
		
		// In case if Product does not have brand as member
		String hql = "select p from Brand b join b.products p"
				+ " where b.name = :BRAND_NAME";
		
		Query qry = session.createQuery(hql);
		qry.setParameter("BRAND_NAME", brandName);
		
		List<Product> list = qry.list();
		for(Product p: list) {
			System.out.printf("%s (%s) -> Rs.%s\n",
				p.getName(), p.getBrand().getName(), p.getUnitPrice());
		}
	}

	static void printProductNamesAndPrices() {
		String hql = "select name, unitPrice from Product";
		Query qry = session.createQuery(hql);
		List<Object[]> list = qry.list();
		for (Object[] data : list) {
			System.out.println(data[0] + " --> Rs." + data[1]);
		}

	}

	static void printProductNames() {
		Query qry = session.createQuery("select name from Product");
		List<String> names = qry.list();
		for(String name: names) {
			System.out.println(name);
		}
	}

	static void printProductsByPage(int pageNum) {
		int pageSize = 10;
		int offset = (pageNum - 1) * pageSize;
		
		Query qry = session.createQuery("from Product");
		qry.setFirstResult(offset);
		qry.setMaxResults(pageSize);
		List<Product> list = qry.list();
		for(Product p: list) {
			System.out.println(p.getId() + " >> " + p.getName() + " --> Rs." + p.getUnitPrice());
		}
	}

	static void printProductsByPriceRange(double min, double max) {
		String hql = "FROM Product WHERE unitPrice BETWEEN ? AND ? ORDER BY unitPrice DESC";
		Query qry = session.createQuery(hql);
		qry.setDouble(0, min);
		qry.setDouble(1, max);
		List<Product> list = qry.list();
		for(Product p: list) {
			System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
		}
	}

	static void printAllBrands() {
		// HQL --> same as SQL but deals with classes and fields
		// A row in a table --> an object of the class
		// A column in a table --> a field/property of the class
		// SQL --> select * from brands
		// HQL --> select b from Brand b
		// Simplified version --> from Brand
		String hql = "from Brand";
		Query qry = session.createQuery(hql);
		
		List<Brand> list = qry.list();
		for(Brand b: list) {
			System.out.println(b.getId() + " --> " + b.getName());
		}
	}
	
}





