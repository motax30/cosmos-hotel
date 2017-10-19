package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.entities.Phone;
import models.util.GenericOperationsBdImpl;
import settings.DatabaseServer;

@SuppressWarnings("rawtypes")
@NoArgsConstructor
public class PhoneData extends GenericOperationsBdImpl implements Datable<Phone, Phone,String>{

	ObjectContainer bd;
	/*
	 * Constructors	
	 */
	public PhoneData(String escope) {
		openBd(escope);
	}
	
	public PhoneData(ObjectContainer phoneData) {
		super();
		this.bd = phoneData;
	}

	/**
	 * @return the customerData
	 */
	public ObjectContainer getPhoneData() {
		return bd;
	}

	/**
	 * @param PhoneData the phoneData to set
	 */
	public void setPhoneData(ObjectContainer PhoneData) {
		this.bd = PhoneData;
	}

	@Override
	public boolean create(Phone pho,String escope) {
		openBd(escope);
		if (exists("number", pho.getNumber())) {
			return false;
		}
		pho.setCreatedAt(LocalDateTime.now());
		pho.setUpdatedAt(LocalDateTime.now());
		if(pho.getUser()!=null) {
			bd.store(pho.getUser());
		}
		bd.store(pho);
		bd.commit();
		return true;
	}

	@Override
	public Phone<Phone> update(Phone pho,String escope) {
		openBd(escope);
		Phone<Phone> currentPhone = findBy("id", pho.getId());
		LocalDateTime createdAt = currentPhone.getCreatedAt();

		BeanUtils.copyProperties(pho, currentPhone);
		currentPhone.setUpdatedAt(LocalDateTime.now());
		currentPhone.setCreatedAt(createdAt);

		bd.store(currentPhone);
		bd.commit();

		return currentPhone;
	}

	@Override
	public boolean delete(Phone pho,String escope) {
		try {
			bd.delete(pho);
			bd.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAll(String escope) {
		for(Phone<Phone>phone : findAll()) {
			bd.delete(phone);
			bd.commit();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Phone<Phone> findBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Phone.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Phone> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	@Override
	public ObjectSet<Phone> findAll() {
		Query query = bd.query();
		query.constrain(Phone.class);
		return query.execute();
	}
	
	@Override
	public ObjectSet<Phone> findAllBy(String key, String value) {
		Query query = bd.query();
		query.constrain(Phone.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public Phone findBy(String entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectSet<Phone> findAllBy(String value) {
		// TODO Auto-generated method stub
		return null;
	}
}