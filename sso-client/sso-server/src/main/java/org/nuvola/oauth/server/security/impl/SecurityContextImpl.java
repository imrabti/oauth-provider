package org.nuvola.oauth.server.security.impl;

import java.security.Principal;

import org.nuvola.oauth.server.NuvolaTokenService;
import org.nuvola.oauth.server.security.SecurityContext;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextImpl implements SecurityContext {
    private final NuvolaTokenService tokenService;

    @Autowired
    SecurityContextImpl(NuvolaTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public UserProfile getCurrentUser(Principal user) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;

        if (oAuth2Authentication.isAuthenticated()) {
            UserProfile profile = tokenService.loadUserProfile(oAuth2Authentication);
            return profile;
        }

        return null;
    }
}
