package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

public class ErrorController extends DefaultServlet {
	
	private static final long serialVersionUID = 3530937468195291083L;
	private static Logger log = Logger.getRootLogger();
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 *  Attributes provided to servlets managing exception handling
		 *  
		 *              Attribute               | Data type
		 *  ------------------------------------+-------------
		 *  javax.servlet.error.status_code 	| (Integer)
		 *  javax.servlet.error.exception_type  | (Class)
		 *  javax.servlet.error.message      	| (String)
		 *  javax.servlet.error.request_uri  	| (String)
		 *  javax.servlet.error.exception    	| (Throwable)
		 *  javax.servlet.error.servlet_name 	| (String)
		 *  
		 */
		
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		
		int status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");
		
		log.trace( throwable == null ? "No exception" : throwable.toString());
		PrintWriter writer = response.getWriter();
		writer.write("Sorry, that's a " + status_code);
		writer.close();
	}

}
