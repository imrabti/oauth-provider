package org.nuvola.oauth.provider.repository;

import java.util.List;

import org.nuvola.oauth.provider.business.Account;
import org.nuvola.oauth.provider.business.User;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUser(User user);
}
