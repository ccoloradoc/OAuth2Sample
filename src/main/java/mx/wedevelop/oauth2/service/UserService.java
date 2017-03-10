package mx.wedevelop.oauth2.service;

import mx.wedevelop.oauth2.model.User;

/**
 * Created by colorado on 9/03/17.
 */
public interface UserService extends CRUDService<User> {
    User findByEmail(String email);
}
