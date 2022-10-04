package it.bitrock.springbootoauth2jwtdemo.mapper;

import it.bitrock.springbootoauth2jwtdemo.model.User;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserMapper {
    private UserMapper(){}

    public static User fromOauth2UserToUser(OAuth2User oauth2User) {
        User user = new User();
        user.setEmail(oauth2User.getAttribute("email"));
        user.setToken(((DefaultOidcUser) oauth2User).getIdToken().getTokenValue());
        return user;
    }
}
