package mx.wedevelop.oauth2.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by colorado on 9/03/17.
 */
public class UserDetailsImpl implements UserDetails {

    /**
     * Default user roles.
     */
    public static final List<GrantedAuthority> DEFAULT_ROLES;

    static {
        DEFAULT_ROLES = new ArrayList<GrantedAuthority>(1);
        GrantedAuthority defaultRole = new SimpleGrantedAuthority("USER");
        DEFAULT_ROLES.add(defaultRole);
    }

    private Collection<SimpleGrantedAuthority> authorities;
    private String username;
    private String password;
    private String email;
    private String picture;
    private Boolean enabled;

    public UserDetailsImpl() {

    }

    public UserDetailsImpl(String name, String email, String picture) {
        this.username = name;
        this.email = email;
        this.picture = picture;
    }


    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
