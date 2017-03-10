package mx.wedevelop.oauth2.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by colorado on 9/03/17.
 */
public class CustomUserDetails extends User {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8788018349438166799L;

    /**
     * Default user roles.
     */
    public static final List<GrantedAuthority> DEFAULT_ROLES;

    static {
        DEFAULT_ROLES = new ArrayList<GrantedAuthority>(1);
        GrantedAuthority defaultRole = new SimpleGrantedAuthority("ROLE_USER");
        DEFAULT_ROLES.add(defaultRole);
    }

    // Added application specific fields here.
    private String firstName;

    /**
     * Create User with default roles.
     *
     * @param username
     * @param password
     * @param authorities
     */
    public CustomUserDetails(String username, String password) {
        super(username, password, DEFAULT_ROLES);
    }

    /**
     * @param username
     * @param password
     * @param authorities
     */
    public CustomUserDetails(String username, String password,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
