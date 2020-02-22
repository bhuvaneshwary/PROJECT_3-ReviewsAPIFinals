package com.udacity.course3.reviews.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*
 * This the document which will be stored in the mongodb with the embedded comment
 */
@Document(collection="reviews")
public class ReviewMongo {
	
	@Id
	private Integer id;
	
	private String review;
	
	private String reviewUsername;
	
	private List<CommentMongo> comments = new ArrayList<CommentMongo>();
	
	public ReviewMongo(Integer id, String review, String reviewUsername, List<CommentMongo> comments)
	{
		this.id = id;
		this.review = review;
		this.reviewUsername = reviewUsername;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReviewUsername() {
		return reviewUsername;
	}

	public void setReviewUsername(String reviewUsername) {
		this.reviewUsername = reviewUsername;
	}

	public List<CommentMongo> getComments() {
		return comments;
	}

	public void setComments(List<CommentMongo> comments) {
		this.comments = comments;
	}

}
