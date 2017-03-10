package mx.wedevelop.oauth2.filter;

import mx.wedevelop.oauth2.model.CustomUserDetails;

import mx.wedevelop.oauth2.service.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * Created by colorado on 9/03/17.
 */
public class GoogleOauth2AuthProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(GoogleOauth2AuthProvider.class);

    @Autowired(required = true)
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("Provider Manager Executed");
        CustomOAuth2AuthenticationToken token = (CustomOAuth2AuthenticationToken) authentication;
        UserDetailsImpl registeredUser = (UserDetailsImpl) token.getPrincipal();
        try {
            registeredUser = (UserDetailsImpl) userDetailsService
                    .loadUserByUsername(registeredUser.getEmail());
        } catch (UsernameNotFoundException usernameNotFoundException) {
            logger.info("User trying google/login not already a registered user. Register Him !!");
        }
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomOAuth2AuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
