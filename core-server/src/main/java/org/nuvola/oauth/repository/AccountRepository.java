package org.nuvola.oauth.repository;

import org.nuvola.oauth.business.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
