package it.bitrock.springbootoauth2jwtdemo.controller;

import it.bitrock.springbootoauth2jwtdemo.model.User;
import it.bitrock.springbootoauth2jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<User> getAccount(@AuthenticationPrincipal OAuth2User oauth2User) {
        return userService.getAccount(oauth2User);
    }
}
