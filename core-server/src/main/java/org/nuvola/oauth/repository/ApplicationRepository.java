package org.nuvola.oauth.repository;

import org.nuvola.oauth.business.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
