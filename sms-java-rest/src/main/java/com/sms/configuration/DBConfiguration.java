package com.sms.configuration;

import com.mongodb.Mongo;
import com.sms.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * This is the DBConfiguration class. 
 * DBProperties are loaded from the src/main/resources.
 * The studentRepository is responsible for CRUD operations.
 * Mongodb is used as the database and spring mongo
 * templates are configured in this class.
 * 
 * @author Dilaj
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
@PropertySource("classpath:mongo.properties")
public class DBConfiguration {

	@Value("${mongo.database}")
	private String database;
	
	@Value("${mongo.host}")
	private String host;
	
	@Value("${mongo.port}")
	private int port;
	
	@Value("${mongo.username}")
	private String username;
	
	@Value("${mongo.password}")
	private String password;
	
	private static final Logger logger = LogManager.getLogger(DBConfiguration.class);

	/**
	 * This method configures the spring mongo template.
	 * if the exception is occured, the bean ll be null.
	 * Otherwise mongo template bean is created.  
	 * @return
	 */
	@Bean
	public MongoTemplate mongoTemplate(){

		try {
			Mongo  mongo = new Mongo(host, port);
			UserCredentials userCredentials = new UserCredentials(username,password);
			MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, database,userCredentials);
			MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
			return mongoTemplate;
		} catch (UnknownHostException e) {
			logger.debug("Exception has occured while initzalizing mongo db : {}", e);
		}
		
		return null;
	}

	/**
	 * This bean configures db.properties with java class attributes.
	 * static key word is used otherwise values ll be null. 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	 
}
