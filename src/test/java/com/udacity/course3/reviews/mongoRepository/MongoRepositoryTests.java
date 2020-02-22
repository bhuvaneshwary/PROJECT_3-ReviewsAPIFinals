package com.udacity.course3.reviews.mongoRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.entity.CommentMongo;
import com.udacity.course3.reviews.entity.ReviewMongo;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoRepositoryTests {
	
	@Autowired
	ReviewMongoRepository reviewMongoRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Test
	public void test1()
	{
		CommentMongo comment = new CommentMongo(1, "What is the warranty?", "WarrantyMan1");
		List<CommentMongo> comments = new ArrayList<CommentMongo>();
		comments.add(comment);
		ReviewMongo review = new ReviewMongo(1, "Good Product!", "HappyCustomer1", comments);
		review = mongoTemplate.save(review, "reviews");
		
		if(reviewMongoRepo.existsById(review.getId()))
		{
			ReviewMongo result = reviewMongoRepo.findById(review.getId()).get();
			Assert.assertTrue(review.getComments().get(0).getCommentUsername().equals(result.getComments().get(0).getCommentUsername()));
			Assert.assertTrue(review.getReview().equals(result.getReview()));
		}
		else
		{
			Assert.fail("Mongo Repository : Id exists returned false!");
		}
	}

}
