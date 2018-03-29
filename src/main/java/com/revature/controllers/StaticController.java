package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

public class StaticController extends DefaultServlet {

	private static final long serialVersionUID = 2768991736859086003L;

	@Override
	protected void	service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Avoid CORS error
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		//Delegate back to the parent service method for HTTP method delegation
		super.service(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		super.doGet(request, response);
	}
}
