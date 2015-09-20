package org.nuvola.oauth.server.controller;

import java.security.Principal;

import org.nuvola.oauth.server.security.SecurityContext;
import org.nuvola.oauth.shared.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.nuvola.oauth.shared.ApiPaths.SESSION;

@RestController
@RequestMapping(value = SESSION)
public class SessionController {
    private final SecurityContext securityContext;

    @Autowired
    SessionController(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserProfile user(Principal user) {
        return securityContext.getCurrentUser(user);
    }
}
