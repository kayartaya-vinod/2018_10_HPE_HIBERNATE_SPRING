package com.hpe.training.programs;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.hpe.training.cfg.AppConfig6;
import com.hpe.training.entity.Product;

public class P04_TestingHibernateTemplate {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig6.class);
		
		HibernateTemplate template = ctx.getBean(HibernateTemplate.class);
		
		
		// Hibernate's Session API
		Product p = template.get(Product.class, 1);
		System.out.println("Name     = " + p.getName());
		System.out.println("Brand    = " + p.getBrand().getName());
		System.out.println("Category = " + p.getCategory().getName());
		
		// Hibernate's Query API
		List<Product> list = (List<Product>) template.find(
				"from Product where unitPrice between ? and ?", 40.0, 50.0);
		
		System.out.println("Products between Rs.40 and 50 are: ");
		for(Product pr: list) {
			System.out.printf("%s --> Rs.%s\n", pr.getName(), pr.getUnitPrice());
		}
		
		// Hibernate's Criteria API
		ProjectionList plist = Projections.projectionList();
		plist.add(Projections.groupProperty("b.name"));
		plist.add(Projections.rowCount());
		
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.createAlias("brand", "b");
		dc.setProjection(plist);

		System.out.println();
		System.out.println("Brandwise product count: ");
		List<Object[]> data = (List<Object[]>) template.findByCriteria(dc);
		for (Object[] d : data) {
			System.out.println(d[0] + " --> " + d[1]);
		}
		
		ctx.close();
		
	}
}



