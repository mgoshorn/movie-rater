package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Movie;
import com.revature.beans.Rating;
import com.revature.util.ConnectionUtil;

public class RatingDao {

	private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
	
	private Rating extractRating(ResultSet rs) throws SQLException{
		int id = rs.getInt("id");
		int movieId = rs.getInt("movie_id");
		int rating = rs.getInt("rating");
		return new Rating(id, movieId, rating);
	}
	
	public List<Rating> getRatings() {
		try(Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM movie_ratings";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			List<Rating> ratings = new ArrayList<>();
			while(result.next()) {
				ratings.add(extractRating(result));
			}
			return ratings;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Rating getRating(int id) {
		try(Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM movie_ratings WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return extractRating(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Rating> getRatingByMovie(Movie movie) {
		try(Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM movie_ratings WHERE movie_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, movie.getId());
			ResultSet result = ps.executeQuery();
			List<Rating> ratings = new ArrayList<>();
			while(result.next()) {
				ratings.add(extractRating(result));
			}
			return ratings;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
