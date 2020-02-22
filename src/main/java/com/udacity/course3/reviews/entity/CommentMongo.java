package com.udacity.course3.reviews.entity;


/*
 * The class is used as an embedded document to ReviewMongo Document
 */
public class CommentMongo {
	
	private Integer id;
	
	private String comment;
	
	private String commentUsername;
	
	public CommentMongo(Integer id, String comment, String commentUsername)
	{
		this.id = id;
		this.comment = comment;
		this.commentUsername = commentUsername;
	}
	
	//Generated Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentUsername() {
		return commentUsername;
	}

	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}
	
	

}
