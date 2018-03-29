package com.revature.beans;

public class Rating {
	private int id;
	private int movieId;
	private int rating;

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public int getMovieId() {
		return movieId;
	}

	public int getRating() {
		return rating;
	}

	public Rating(int id, int movieId, int rating) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", movieId=" + movieId + ", rating=" + rating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + movieId;
		result = prime * result + rating;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (id != other.id)
			return false;
		if (movieId != other.movieId)
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
