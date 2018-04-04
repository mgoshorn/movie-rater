package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Movie;
import com.revature.services.MovieService;

public class MovieController extends DefaultServlet {

	private static final long serialVersionUID = -2721418389249762586L;
	private static final Logger log = Logger.getRootLogger();
	private static final MovieService service = new MovieService();
	
	@Override
	protected void	service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Avoid CORS error
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		//Delegate back to the parent service method for HTTP method delegation
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.trace(request.getRequestURI());
		String context = request.getRequestURI().substring(request.getContextPath().length() + "Movie/".length());
		
		if(context.length() <= 1) {
			handleGetAllMovies(response);
			return;
		}
		
		try {
			int id = Integer.parseInt(context.split("/")[1]);
			handleGetMovie(id, response);
			return;
		} catch(NumberFormatException e) {
			//Note: response.setStatus(404) will not work!
			response.sendError(404);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		log.trace("Post received");
		
		log.warn(getServletContext().getInitParameter("MyParam"));
		log.trace(getServletConfig().getInitParameter("movieParam"));
		
		
		
		
		String json = "";
		
		try {
			
			//Get JSON string
			json = request.getReader().lines().reduce("", (a, b) -> (a+b));
			//output here
			log.trace(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Unmarshalling JSON
		ObjectMapper om = new ObjectMapper();
		Movie movie = new Movie();
		try {
			movie = om.readValue(json, Movie.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.trace(movie);
		movie.setName(movie.getName() + " II");
		
		try {
			om.writeValue(response.getWriter(), movie);
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
		
		// Handling post data
		
		
		
//		request.getParameterMap().keySet().stream().forEach(log::trace);
//		log.warn(request.getParameter("textInput"));
//		log.warn(request.getParameter("password"));
//		
//		try {
//			response.getWriter().write("Hello " + request.getParameter("textInput"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}
	
	private void handleGetAllMovies(HttpServletResponse response) {
		log.trace("Getting all movies");
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
	
	private void handleGetMovie(int id, HttpServletResponse response) {
		log.trace("Getting all movies");
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
