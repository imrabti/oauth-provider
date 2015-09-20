package org.nuvola.oauth.repository;

import java.util.List;

import org.nuvola.oauth.business.Account;
import org.nuvola.oauth.business.User;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(User user);
}
