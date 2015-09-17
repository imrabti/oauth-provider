package org.nuvola.oauth.repository;

import org.nuvola.oauth.business.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
