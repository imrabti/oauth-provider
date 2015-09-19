package org.nuvola.oauth.repository;

import org.nuvola.oauth.business.Account;
import org.nuvola.oauth.business.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(User user);
}
