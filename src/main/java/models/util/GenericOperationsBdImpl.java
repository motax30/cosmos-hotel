package models.util;

import com.db4o.ObjectContainer;

import models.enumerates.Scope;
import settings.DatabaseServer;
import settings.DatabaseServerTest;

public class GenericOperationsBdImpl implements GenericOperationsBd<Object> {
	protected static ObjectContainer bd;
	@SuppressWarnings("static-access")
	@Override
	public boolean openBd(String escope) {
		if(bd==null||bd.ext().isClosed()) {
			if(escope==Scope.PRODUCAO.toString()) {
				this.bd = DatabaseServer.getServer().openClient();
				return true;
				}else {
					this.bd = DatabaseServerTest.getServer().openClient();
					return true;
				}
			}else if(!bd.close()){
				return true;
			}
		return false;
	}

	@Override
	public boolean closeBd() {
		return bd.close();
	}

	@Override
	public boolean gravarBd(Object entity) {
		bd.store(entity);
		bd.commit();
		return false;
	}

	@Override
	public Object delEntityToBd(Object entity) {
		Object objDeletar = entity;
		bd.delete(entity);
		bd.commit();// TODO Auto-generated method stub
		return objDeletar;
	}

}
