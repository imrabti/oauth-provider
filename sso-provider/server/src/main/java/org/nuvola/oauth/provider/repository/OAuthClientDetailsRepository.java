package org.nuvola.oauth.provider.repository;

import org.nuvola.oauth.provider.server.business.OAuthClientDetails;
import org.springframework.data.repository.CrudRepository;

public interface OAuthClientDetailsRepository extends CrudRepository<OAuthClientDetails, String> {
}
