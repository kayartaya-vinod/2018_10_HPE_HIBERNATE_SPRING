package com.hpe.training.programs;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Enumeration<Driver> en = DriverManager.getDrivers();
		while(en.hasMoreElements()) {
			Object d = en.nextElement();
			System.out.println(d.getClass());
		}
	}
}
