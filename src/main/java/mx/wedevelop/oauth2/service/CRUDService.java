package mx.wedevelop.oauth2.service;

import java.util.List;

/**
 * Created by colorado on 9/03/17.
 */
public interface CRUDService<T> {
    List<T> findAll();
    T findById(int id);
    T saveOrUpdate(T object);
    void delete(int id);
}
