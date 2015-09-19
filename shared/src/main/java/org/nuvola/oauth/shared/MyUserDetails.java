/*
 * Copyright (c) 2014 by Intelcia Group, Inc., All rights reserved.
 * This source code, and resulting software, is the confidential and proprietary information
 * ("Proprietary Information") and is the intellectual property ("Intellectual Property")
 * of Intelcia Group, Inc. ("The Company"). You shall not disclose such Proprietary Information and
 * shall use it only in accordance with the terms and conditions of any and all license
 * agreements you have entered into with The Company.
 */

package org.nuvola.oauth.shared;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {
    private UserProfile userProfile;
    private String password;
    private List<GrantedAuthority> authorities;
    private Boolean accountNonLocked;

    public MyUserDetails() {
        this.accountNonLocked = true;
    }

    public MyUserDetails(UserProfile userProfile, String password, List<GrantedAuthority> authorities) {
        this.password = password;
        this.userProfile = userProfile;
        this.authorities = authorities;
        this.accountNonLocked = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userProfile.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}
