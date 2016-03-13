package org.nuvola.oauth.server.security.impl;

import java.security.Principal;
import java.util.Map;

import org.nuvola.oauth.server.security.SecurityContext;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SecurityContextImpl implements SecurityContext {
    private final ObjectMapper objectMapper;

    @Autowired
    SecurityContextImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public UserProfile getCurrentUser(Principal user) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();

        if (oAuth2Authentication.isAuthenticated() && authentication != null && authentication.getDetails() != null) {
            Map principal = (Map) ((Map) authentication.getDetails()).get("principal");
            UserProfile profile = objectMapper.convertValue(principal.get("userProfile"), UserProfile.class);

            return profile;
        }

        return null;
    }
}
