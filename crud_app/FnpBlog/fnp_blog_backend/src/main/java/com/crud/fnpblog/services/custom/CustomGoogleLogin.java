package com.crud.fnpblog.services.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomGoogleLogin implements OAuth2User {
    private final OAuth2User oauth2User;

    @Override
    public Map<String, Object> getAttributes() {
        System.out.println("------------into getAttributes method of customGoogleLogin class----------");
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("------------into GetAuthorities method of customGoogleLogin class----------");
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        System.out.println("------------into GetName method of customGoogleLogin class----------");
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        System.out.println("------------into GetEmail method of customGoogleLogin class----------");
        return oauth2User.getAttribute("email");
    }
}

