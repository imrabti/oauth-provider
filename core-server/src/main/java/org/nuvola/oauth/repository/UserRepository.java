package org.nuvola.oauth.repository;

import org.nuvola.oauth.business.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
