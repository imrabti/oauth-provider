package org.nuvola.oauth.server.security.impl;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.nuvola.oauth.server.security.SecurityContext;
import org.nuvola.oauth.shared.ApplicationAuthority;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextImpl implements SecurityContext {
    @Override
    public UserProfile getCurrentUser(Principal user) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;
        Authentication authentication = oAuth2Authentication.getUserAuthentication();

        if (oAuth2Authentication.isAuthenticated() && authentication != null && authentication.getDetails() != null) {
            Map<String, Object> principal = (Map) ((Map) authentication.getDetails()).get("principal");
            Map<String, Object> info = (Map) principal.get("userProfile");

            UserProfile profile = new UserProfile();
            profile.setUserName((String) info.get("userName"));
            profile.setFirstName((String) info.get("firstName"));
            profile.setLastName((String) info.get("lastName"));
            profile.setEmail((String) info.get("email"));

            if (info.containsKey("authorities")) {
                List<Map<String, Object>> authorities = (List) info.get("authorities");
                for (Map<String, Object> authority : authorities) {
                    ApplicationAuthority applicationAuthority = new ApplicationAuthority();
                    applicationAuthority.setClientId((String) authority.get("clientId"));
                    applicationAuthority.setAuthority((String) authority.get("authority"));
                    profile.getAuthorities().add(applicationAuthority);
                }
            }

            return profile;
        }

        return null;
    }
}
