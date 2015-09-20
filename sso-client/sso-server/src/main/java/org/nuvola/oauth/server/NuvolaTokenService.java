package org.nuvola.oauth.server;

import java.util.List;
import java.util.Map;

import org.nuvola.oauth.shared.ApplicationAuthority;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class NuvolaTokenService extends DefaultTokenServices {
    private final TokenStore tokenStore;

    @Autowired
    NuvolaTokenService(TokenStore tokenStore) {
        setTokenStore(tokenStore);

        this.tokenStore = tokenStore;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException,
            InvalidTokenException {
        OAuth2Authentication result = super.loadAuthentication(accessTokenValue);
        result.setAuthenticated(true);

        return result;
    }

    public UserProfile loadUserProfile(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken token = tokenStore.readAccessToken(details.getTokenValue());

        // Load additional information
        Map<String, Object> info = (Map) token.getAdditionalInformation().get("profile");

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
}
