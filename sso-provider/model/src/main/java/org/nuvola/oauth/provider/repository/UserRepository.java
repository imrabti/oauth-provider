package org.nuvola.oauth.provider.repository;

import org.nuvola.oauth.provider.business.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
