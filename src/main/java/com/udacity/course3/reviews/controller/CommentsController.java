package com.udacity.course3.reviews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.CommentMongo;
import com.udacity.course3.reviews.entity.ReviewMongo;
import com.udacity.course3.reviews.exception.IdNotFoundException;
import com.udacity.course3.reviews.mongoRepository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	ReviewRepository reviewRepo;
	
	/*
	 * MongoDb repository for storing the embedded comments document
	 */
	@Autowired
	ReviewMongoRepository reviewMongoRepo;

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
    	if(reviewRepo.existsById(reviewId))
        {
    		commentRepo.save(comment);//Saved comment to fetch the ID to be saved in Mongo
    		
    		/*
    		 * Check if review Id exists in mongo and if yes, add the comment to the review
    		 */
    		ReviewMongo reviewMongo = null;
    		if(reviewMongoRepo.existsById(reviewId))
    		{
    			reviewMongo = reviewMongoRepo.findById(reviewId).get();
    			List<CommentMongo> comments = reviewMongo.getComments();
    			if(comments == null)
    			{
    				comments = new ArrayList<CommentMongo>();
    			}
    			CommentMongo commentMongo = new CommentMongo(comment.getId(), comment.getComment(), comment.getCommentUsername());
    			comments.add(commentMongo);
    			reviewMongo.setComments(comments);
    		}
        	return new ResponseEntity<ReviewMongo>(reviewMongoRepo.save(reviewMongo), HttpStatus.OK);
        }
        else
        {
        	return new ResponseEntity<String>("The given review ID does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
    	if(reviewRepo.existsById(reviewId))
    	{
    		return commentRepo.findAllByReviewId(reviewId);
    	}
    	throw new IdNotFoundException();
    }
}