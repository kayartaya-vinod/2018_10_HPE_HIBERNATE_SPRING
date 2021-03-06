package com.hpe.training.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8080/spring-mvc/hello
@WebServlet(urlPatterns = { "/hello" })
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Some client request received at " + new Date());
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello, from Servlet!</h1>");
		out.println("<hr>");
		out.println("<em>Developed by Vinod</em>");
		out.close();
	}
	
}
