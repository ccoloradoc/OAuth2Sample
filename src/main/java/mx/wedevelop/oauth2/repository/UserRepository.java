package mx.wedevelop.oauth2.repository;

import mx.wedevelop.oauth2.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by colorado on 9/03/17.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
