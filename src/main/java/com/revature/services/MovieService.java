package com.revature.services;

import java.util.List;

import com.revature.beans.Movie;
import com.revature.dao.MovieDao;

public class MovieService {

	private static final MovieDao dao = new MovieDao();
	
	public List<Movie> getAllMovies() {
		return dao.getMovies();
	}

	public Object getMovie(int id) {
		return dao.getMovie(id);
	}
}
