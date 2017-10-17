package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import models.entities.Customer;

public interface Datable<T,P,J> {
	public boolean create(P obj);
	public T update(P obj);
	public boolean delete(P obj);
	public void deleteAll();
	public boolean exists(String key, String value);
	public T findBy(String key, String value);
	public ObjectSet<T> findAll();
	public ObjectSet<T> findAllBy(String key,J value);
	public boolean closeConnection(ObjectContainer conexao);
}
