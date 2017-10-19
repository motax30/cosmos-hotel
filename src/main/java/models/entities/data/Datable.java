package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import models.entities.Customer;

public interface Datable<T,P,J> {
	public boolean create(P obj,String escope);
	public T update(P obj,String escope);
	public boolean delete(P obj,String escope);
	public void deleteAll(String escope);
	public boolean exists(String key, String value);
	public T findBy(J entity);
	public T findBy(J entity,J entity2);
	public ObjectSet<T> findAll();
	public ObjectSet<T> findAllBy(J key,J value);
	public ObjectSet<T> findAllBy(J value);
}
