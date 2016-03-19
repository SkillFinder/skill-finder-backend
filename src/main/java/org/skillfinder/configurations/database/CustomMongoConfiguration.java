package org.skillfinder.configurations.database;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


public abstract class CustomMongoConfiguration extends AbstractMongoConfiguration {


    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost",27017);
    }

    @Bean(name="mongoTemplate")
    MongoTemplate createMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean(name="gridFsTemplate")
    GridFsTemplate createGridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(),mappingMongoConverter());
    }

}
