package it.bitrock.springbootoauth2jwtdemo.config;

import it.bitrock.springbootoauth2jwtdemo.model.Account;
import it.bitrock.springbootoauth2jwtdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EnableWebSecurity
public class OAuth2LoginSecurityConfig {

    @Autowired
    AccountRepository accountRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/login").permitAll()
                        .antMatchers("/curriculum").hasRole("HR")
                        .antMatchers(HttpMethod.GET, "/curriculum/{\\d+}").hasRole("DEV")
//                        .antMatchers(HttpMethod.DELETE, "/curriculum/{\\d+}").hasAuthority("curriculum:write")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
//                    .clientRegistrationRepository(this.clientRegistrationRepository())
//                    .authorizedClientRepository(this.authorizedClientRepository())
//                    .authorizedClientService(this.authorizedClientService())
//                                .loginPage("/login")
//                    .authorizationEndpoint(authorization -> authorization
//                            .baseUri(this.authorizationRequestBaseUri())
//                            .authorizationRequestRepository(this.authorizationRequestRepository())
//                            .authorizationRequestResolver(this.authorizationRequestResolver())
//                    )
//                    .redirectionEndpoint(redirection -> redirection
//                            .baseUri(this.authorizationResponseBaseUri())
//                    )
//                    .tokenEndpoint(token -> token
//                            .accessTokenResponseClient(this.accessTokenResponseClient())
//                    )
                                .userInfoEndpoint(userInfo -> userInfo
                                        .oidcUserService(oidcUserService())
                                )
                );
        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return (userRequest) -> {
            // Delegate to the default implementation for loading a user
            OidcUser oidcUser = delegate.loadUser(userRequest);

            OAuth2AccessToken accessToken = userRequest.getAccessToken();
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            // TODO
            // 1) Fetch the authority information from the protected resource using accessToken
            // 2) Map the authority information to one or more GrantedAuthority's and add it to mappedAuthorities
            Account account = accountRepository.findAccountByUsername(oidcUser.getEmail());
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().getName()));

            // 3) Create a copy of oidcUser but use the mappedAuthorities instead
            oidcUser = new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());

            return oidcUser;
        };
    }
}
