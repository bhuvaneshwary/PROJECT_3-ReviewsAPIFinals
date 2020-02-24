package com.udacity.course3.reviews;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.udacity.course3.reviews.mongoRepository")//To enable useof mongo repositories and mongodb
public class ReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
		
		/* ******************** Flyway to create tables in the db *********** */
		
		
		
		//If a schema already exists we use baseline to create a baseline if it doesn't exist
		//flyway.baseline();
		
		//To correct any errors in the flyway migrations, before starting a new migration
		//flyway.repair();
	
		
	}
	
	//With reference from the student hub chat regarding the issue with testing
	@Bean
	public MongoClient mongo() throws Exception 
	{ 
		return new MongoClient("localhost");
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception
	{
		return new MongoTemplate(mongo(), "jdnd_p03");
	}

}
