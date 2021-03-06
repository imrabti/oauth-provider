package org.nuvola.oauth.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<ApplicationAuthority> authorities;

    public UserProfile() {
        authorities = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ApplicationAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<ApplicationAuthority> authorities) {
        this.authorities = authorities;
    }
}
