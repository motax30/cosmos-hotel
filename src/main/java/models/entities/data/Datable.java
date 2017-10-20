package models.entities.data;

import com.db4o.ObjectSet;

import models.enumerates.Scope;

public interface Datable<T,P,J> {
	public boolean create(P obj,String escope);
	public T update(P obj,String escope);
	public boolean delete(P obj,String escope);
	public void deleteAll(String escope);
	public boolean exists(String key, String value);
	public T findBy(J entity,String escope);
	public T findBy(J entity,J entity2,String escope);
	public ObjectSet<T> findAll(String scope);
	public ObjectSet<T> findAllBy(J key,J value,String escope);
	public ObjectSet<T> findAllBy(J value,String escope);
	boolean exists(String key, String value, String escope);
	public void isBdNullOrClosed(String escope);
}
