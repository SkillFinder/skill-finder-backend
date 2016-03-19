package org.skillfinder.configurations;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

@Configuration
public class MongoConfiguration {

    @Bean(name="mongoTemplate")
    @Profile("dev")
    MongoTemplate createDevMongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory("localhost",27017,"skill-finder-dev"));
    }


    @Bean(name="mongoTemplate")
    @Profile("test")
    MongoTemplate createTestMongoTemplate() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory("localhost",27017,"skill-finder-test"));
    }

    private MongoDbFactory mongoDbFactory(String host, int port, String dbName) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(host, port);
        return new SimpleMongoDbFactory(mongoClient,dbName);
    }

}
