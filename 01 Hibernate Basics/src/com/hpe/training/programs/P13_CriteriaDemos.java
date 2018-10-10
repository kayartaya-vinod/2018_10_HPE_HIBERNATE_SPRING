package com.hpe.training.programs;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hpe.training.entity.Brand;
import com.hpe.training.entity.Product;
import com.hpe.training.utils.HibernateUtil;

@SuppressWarnings("unchecked")
public class P13_CriteriaDemos {

	private static Session session;

	public static void main(String[] args) {
		SessionFactory factory = null;

		try {
			factory = HibernateUtil.getSessionFactory();
			session = factory.openSession();

			// printAllBrands();
			// printProductsByPriceRange(40.0, 50.0);
			// printProductNamesAndPrices();
			// printProductsByBrand("Zespri");
			printBrandwiseProductCount();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

	static void printBrandwiseProductCount() {
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.groupProperty("b.name"));
		plist.add(Projections.rowCount());

		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("brand", "b");
		cr.setProjection(plist);

		List<Object[]> list = cr.list();
		for (Object[] data : list) {
			System.out.println(data[0] + " --> " + data[1]);
		}
	}

	static void printProductsByBrand(String brandName) {
		Criteria cr = session.createCriteria(Product.class);
		cr.createAlias("brand", "b");
		cr.add(Restrictions.eq("b.name", brandName));
		List<Product> list = cr.list();
		for (Product p : list) {
			System.out.printf("%s (%s) --> Rs.%s\n", p.getName(), p.getBrand().getName(), p.getUnitPrice());
		}
	}

	static void printProductNamesAndPrices() {
		String[] fields = { "name", "unitPrice" };
		ProjectionList plist = Projections.projectionList();
		for (String field : fields) {
			plist.add(Projections.property(field));
		}

		Criteria cr = session.createCriteria(Product.class);
		cr.setProjection(plist);

		List<Object[]> list = cr.list();
		for (Object[] data : list) {
			System.out.println(data[0] + " --> Rs." + data[1]);
		}
	}

	static void printProductsByPriceRange(double min, double max) {
		Criteria cr = session.createCriteria(Product.class);
		cr.add(Restrictions.ge("unitPrice", min));
		cr.add(Restrictions.le("unitPrice", max));

		cr.addOrder(Order.desc("unitPrice"));
		cr.addOrder(Order.asc("name"));

		List<Product> list = cr.list();
		for (Product p : list) {
			System.out.println(p.getName() + " --> Rs." + p.getUnitPrice());
		}
	}

	static void printAllBrands() {
		Criteria cr = session.createCriteria(Brand.class);
		List<Brand> list = cr.list();
		for (Brand b : list) {
			System.out.println(b);
		}
	}

}
