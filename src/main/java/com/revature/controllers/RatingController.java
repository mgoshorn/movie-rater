package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.RatingService;

public class RatingController extends DefaultServlet {
	private static final long serialVersionUID = 5429905123606366031L;
	private static final Logger log = Logger.getRootLogger();
	private static final RatingService service = new RatingService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		log.trace(request.getRequestURI());
		
		
		// Forward
//		try {
//			request.getRequestDispatcher("/Movie/3").forward(request, response);
//		} catch (ServletException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		};
		
		
		//Redirect to Google
		try {
			response.sendRedirect("http://localhost:8080/MovieRater/Movie/3");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		
		String context = request.getRequestURI().substring("/MovieRater/Rating".length());
		
		if(context.length() <= 1) {
			handleGetAllRatings(response);
			return;
		}
		
		try {
			int id = Integer.parseInt(context.split("/")[1]);
			handleGetRating(id, response);
			return;
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		response.setStatus(404);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		log.warn(getServletContext().getInitParameter("MyParam"));
		log.trace(getServletConfig().getInitParameter("movieParam"));
	}

	private void handleGetAllRatings(HttpServletResponse response) {
		log.trace("Getting all ratings");
		ObjectMapper om = new ObjectMapper();
		try {
			om.writeValue(response.getWriter(), service.getAllMovies());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleGetRating(int id, HttpServletResponse response) {
		log.trace("Getting rating");
		ObjectMapper om = new ObjectMapper();
		try {
			om.writeValue(response.getWriter(), service.getMovie(id));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
