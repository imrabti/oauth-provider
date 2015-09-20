package org.nuvola.oauth.server.security;

import java.security.Principal;

import org.nuvola.oauth.shared.UserProfile;

public interface SecurityContext {
    UserProfile getCurrentUser(Principal user);
}
