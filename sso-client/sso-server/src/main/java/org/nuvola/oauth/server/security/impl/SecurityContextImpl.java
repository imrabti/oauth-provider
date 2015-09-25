package org.nuvola.oauth.server.security.impl;

import java.security.Principal;

import org.nuvola.oauth.server.security.SecurityContext;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextImpl implements SecurityContext {
    @Override
    public UserProfile getCurrentUser(Principal user) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;

        if (oAuth2Authentication.getUserAuthentication().isAuthenticated()) {
            UserProfile profile = new UserProfile();
            return profile;
        }

        return null;
    }
}
