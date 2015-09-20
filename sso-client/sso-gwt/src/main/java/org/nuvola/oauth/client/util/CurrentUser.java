package org.nuvola.oauth.client.util;

import java.util.ArrayList;
import java.util.List;

import org.nuvola.oauth.shared.ApplicationAuthority;
import org.nuvola.oauth.shared.UserProfile;

public class CurrentUser {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> authorities;
    boolean loggedIn;
    boolean allowed;

    public void initCurrentUser(UserProfile profile, String clientId) {
        loggedIn = true;
        allowed = false;
        userName = profile.getUserName();
        firstName = profile.getFirstName();
        lastName = profile.getLastName();
        email = profile.getEmail();
        authorities = new ArrayList<>();

        for (ApplicationAuthority authority : profile.getAuthorities()) {
            if (authority.getClientId().equals(clientId)) {
                authorities.add(authority.getAuthority());
            }
        }

        allowed = !authorities.isEmpty();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean hasAuthority(String... authorities) {
        for (String authority : authorities) {
            if (this.authorities.contains(authority)) {
                return true;
            }
        }

        return false;
    }
}
