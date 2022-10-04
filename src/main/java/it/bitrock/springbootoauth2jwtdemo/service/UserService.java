package it.bitrock.springbootoauth2jwtdemo.service;

import it.bitrock.springbootoauth2jwtdemo.mapper.UserMapper;
import it.bitrock.springbootoauth2jwtdemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseEntity<User> getAccount(OAuth2User oauth2User) {
        return new ResponseEntity<>(
                UserMapper.fromOauth2UserToUser(oauth2User),
                HttpStatus.OK
        );
    }
}
