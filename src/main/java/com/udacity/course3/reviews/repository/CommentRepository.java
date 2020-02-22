package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udacity.course3.reviews.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	/*
	 * A query generated automatically at runtime by the repository based on
	 * the entity field name.
	 * It returns all the cooments where the reviewId matches the input
	 * @param : Integer reviewId to search with
	 * @result: List of Comment entities have review Id same as @param
	 */
	List<Comment> findAllByReviewId(Integer reviewId);

}