package com.udacity.course3.reviews.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	/*@Column(name="`product_id`")The foreign key column
	private Integer productId;
	*/
	
	@ManyToOne//Owner of the relationship mapping
	@JoinColumn(referencedColumnName = "id")// referes to id column of Product table
	@JsonBackReference//https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue/18288939#18288939
	private Product product;
	
	@Column(name="review")
	private String review;
	
	@Column(name="review_username")
	private String reviewUsername;
	
	@OneToMany(mappedBy = "review")//Relationship mapped by review field in Comment entity
	@JsonManagedReference
	private List<Comment> comments = new ArrayList<Comment>();

	
	

	//Generated Getters and Setters
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

}