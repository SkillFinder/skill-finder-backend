package org.skillfinder.configurations.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class MongoDevConfiguration extends CustomMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "skill-finder-dev";
    }

}
