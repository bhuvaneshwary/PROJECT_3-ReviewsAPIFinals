package com.udacity.course3.reviews.mongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udacity.course3.reviews.entity.ReviewMongo;

public interface ReviewMongoRepository extends MongoRepository<ReviewMongo, Integer> {

}
