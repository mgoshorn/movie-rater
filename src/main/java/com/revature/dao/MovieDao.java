package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Movie;
import com.revature.util.ConnectionUtil;

public class MovieDao {
	
	private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
	
	private Movie extractMovie(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		return new Movie(id, name);
	}
	
	public List<Movie> getMovies() {
		try(Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM movies";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			List<Movie> movies = new ArrayList<>();
			while(result.next()) {
				movies.add(extractMovie(result));
			}
			return movies;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Movie getMovie(int id) {
		try(Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM movies WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if(result.next()) {
				return extractMovie(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
