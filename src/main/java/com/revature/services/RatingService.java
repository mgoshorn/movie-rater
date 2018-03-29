package com.revature.services;

import java.util.List;

import com.revature.beans.Rating;
import com.revature.dao.RatingDao;

public class RatingService {

	private static final RatingDao dao = new RatingDao();
	
	public List<Rating> getAllMovies() {
		return dao.getRatings();
	}

	public Rating getMovie(int id) {
		return dao.getRating(id);
	}

}
