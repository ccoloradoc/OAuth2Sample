package mx.wedevelop.oauth2.config;

import javax.annotation.Resource;

import mx.wedevelop.oauth2.filter.GoogleOAuth2Filter;
import mx.wedevelop.oauth2.filter.GoogleOauth2AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
/**
 * Created by colorado on 9/03/17.
 */
@Configuration
@EnableGlobalAuthentication
@EnableOAuth2Client
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@PropertySource(value = {"classpath:oauth.properties"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    @Autowired
    private OAuth2ClientContextFilter oAuth2ClientContextFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.
                authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login","/public/**", "/resources/**","/resources/public/**").permitAll()
                .antMatchers("/google_oauth2_login").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .and()
                .rememberMe()
                .and()
                .addFilterAfter(oAuth2ClientContextFilter,ExceptionTranslationFilter.class)
                .addFilterAfter(googleOAuth2Filter(),OAuth2ClientContextFilter.class)
                .userDetailsService(userDetailsService);
        // @formatter:on
    }

    @Bean
    @ConfigurationProperties("google.client")
    public OAuth2ProtectedResourceDetails auth2ProtectedResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(auth2ProtectedResourceDetails(),
                new DefaultOAuth2ClientContext(accessTokenRequest));
    }


    @Bean
    public GoogleOAuth2Filter googleOAuth2Filter() {
        return new GoogleOAuth2Filter("/google_oauth2_login");
    }

    /*
    *  Building our custom Google Provider
    * */
    @Bean
    public GoogleOauth2AuthProvider googleOauth2AuthProvider() {
        return new GoogleOauth2AuthProvider();
    }

    /*
    *  Using autowired to assign it to the auth manager
    * */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(googleOauth2AuthProvider());
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
