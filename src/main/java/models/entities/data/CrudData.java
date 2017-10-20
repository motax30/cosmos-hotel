package models.entities.data;

import com.db4o.ObjectSet;

public interface CrudData<T> {
    boolean create(T obj);
    T update(T obj) throws NoSuchMethodException;
    boolean delete(T obj);
    void deleteAll();
    boolean exists(String key, String value);
    T findBy(String key, String value);
    ObjectSet<T> findAll();
    ObjectSet<T> findAllBy(String key, String value);
}
