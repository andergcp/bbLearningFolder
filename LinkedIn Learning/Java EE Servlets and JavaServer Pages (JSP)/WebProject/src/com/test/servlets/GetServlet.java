package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetServlet extends HTTPServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String htmlResponse = "<html><h3>Welcome to Sevlets!</h3></html>";
		PrintWriter writer = resp.getWriter();
		writer.write(htmlResponse);
	}

}
