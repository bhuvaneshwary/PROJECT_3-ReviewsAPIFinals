package com.udacity.course3.reviews.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
	
	private static Product product;
	private static Review review;
	private static Comment comment;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	ReviewRepository reviewRepo;
	
	@Autowired
	CommentRepository commentRepo;

	@Test
	public void test1(){ //verify if the autowired components are not null;
		
		Assert.assertNotNull(entityManager);
		Assert.assertNotNull(prodRepo);
		Assert.assertNotNull(reviewRepo);
		Assert.assertNotNull(commentRepo);
		
	}
	
	@Test
	public void test2(){
		
		/*
		 * Testing if the Product is added successfully
		 */
		product = new Product();
		product.setProductName("MilkyBar");
		product.setDescription("Sweet bar made of white chocolate.");
		product.setCost(1.25f);
		entityManager.persist(product);
		//Product result = prodRepo.findByProductName("MilkyBar");
		//prodRepo.save(product);
		Optional<Product> resP = prodRepo.findById(product.getId());
		if(resP.isPresent())
		{
			Product result = resP.get();
			Assert.assertTrue(product.getId().intValue() == result.getId().intValue());
			Assert.assertTrue(product.getCost().floatValue() == result.getCost().floatValue());
			Assert.assertTrue(product.getDescription().equals(result.getDescription()));
		}
		else
		{
			Assert.fail("The product Find By Id was either empty or null!");
		}
		
		/*
		 * Testing if the Review is added successfully and associated to product
		 */
		review = new Review();
		review.setReview("Very Tasty. Love it.");
		review.setReviewUsername("WhiteChocolate");
		review.setProduct(product);
		entityManager.persist(review);
		//reviewRepo.save(review);
		Optional<Review> resR = reviewRepo.findById(review.getId());
		if(resR.isPresent())
		{
			Review result = resR.get();
			Assert.assertTrue(review.getId().intValue() == result.getId().intValue());
			Assert.assertTrue(review.getReview().equals(result.getReview()));
			Assert.assertTrue(review.getReviewUsername().equals(result.getReviewUsername()));
			Assert.assertTrue(review.getProduct().getId() == result.getProduct().getId());
		}
		else
		{
			Assert.fail("Find Review by Id failed or returned null or empty!");
		}
		
		/*
		 * Testing if comment is successfully added and associated with review
		 */
		comment = new Comment();
		comment.setComment("Totally agree");
		comment.setCommentUsername("Agreeable");
		comment.setReview(review);
		entityManager.persist(comment);
		//commentRepo.save(comment);
		Optional<Comment> resC = commentRepo.findById(comment.getId());
		if(resC.isPresent())
		{
			Comment result = resC.get();
			Assert.assertTrue(comment.getId().intValue() == result.getId().intValue());
			Assert.assertTrue(comment.getComment().equals(result.getComment()));
			Assert.assertTrue(comment.getCommentUsername().equals(result.getCommentUsername()));
			Assert.assertTrue(comment.getReview().getId() == result.getReview().getId());
		}
		else
		{
			Assert.fail("Find Comment by Id failed or returned null or empty!");
		}
		
		
		
		/*
		 * To check if find all comments by reviewId works
		 */
		List<Comment> commentsByReviewId = commentRepo.findAllByReviewId(review.getId());
		Assert.assertTrue(commentsByReviewId.get(0).getComment().equals(comment.getComment()));
		
		/*
		 * To check if find all reviews by productId works
		 */
		List<Review> reviewsByProductId = reviewRepo.findByProductId(product.getId());
		Assert.assertTrue(reviewsByProductId.get(0).getReview().equals(review.getReview()));
	}
	
}