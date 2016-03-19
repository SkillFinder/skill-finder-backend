package org.skillfinder.configurations.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class MongoTestConfiguration extends CustomMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "skill-finder-test";
    }
}