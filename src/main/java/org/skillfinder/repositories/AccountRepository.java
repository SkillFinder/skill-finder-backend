package org.skillfinder.repositories;

import org.skillfinder.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    public Account findByUsername(String username);
}
