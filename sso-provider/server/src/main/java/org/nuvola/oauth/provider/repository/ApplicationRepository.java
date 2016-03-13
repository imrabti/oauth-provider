package org.nuvola.oauth.provider.repository;

import org.nuvola.oauth.provider.server.business.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Application findByClientId(String clientId);
}
