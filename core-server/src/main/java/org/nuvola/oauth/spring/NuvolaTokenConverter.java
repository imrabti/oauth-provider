package org.nuvola.oauth.spring;

import org.nuvola.oauth.shared.MyUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class NuvolaTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

        info.put("email", user.getUserProfile().getEmail());
        info.put("firstName", user.getUserProfile().getFirstName());
        info.put("lastName", user.getUserProfile().getLastName());
        info.put("id", user.getUserProfile().getId());
        info.put("userName", user.getUserProfile().getUserName());

        DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
        customAccessToken.setAdditionalInformation(info);

        return super.enhance(customAccessToken, authentication);
    }
}
