package mx.wedevelop.oauth2.service.security;

import mx.wedevelop.oauth2.converter.UserToUserDetails;
import mx.wedevelop.oauth2.model.User;
import mx.wedevelop.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by colorado on 9/03/17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    private UserToUserDetails userToUserDetails;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserToUserDetails(UserToUserDetails userToUserDetails) {
        this.userToUserDetails = userToUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        if(user == null) {
            throw new UsernameNotFoundException("Could not found user name + " + s);
        }
        return userToUserDetails.convert(user);
    }
}
