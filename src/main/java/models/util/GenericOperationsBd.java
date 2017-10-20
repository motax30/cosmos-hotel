package models.util;

public interface GenericOperationsBd<T> {
	public boolean openBd(String escope);
	public boolean closeBd();
	public boolean gravarBd(T entity);
	public Object delEntityToBd(T entity);
}
