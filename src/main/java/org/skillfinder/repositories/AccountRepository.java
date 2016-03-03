package org.skillfinder.repositories;

import org.skillfinder.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUsername(String username);
}
