package models.entities.data;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import models.entities.Phone;
import settings.DatabaseServer;

@SuppressWarnings("rawtypes")
public class PhoneData implements Datable<Phone, Phone,String>{

	ObjectContainer phoneData;
	/*
	 * Constructors	
	 */
	public PhoneData() {
		phoneData = DatabaseServer.getServer().openClient();
	}
	
	public PhoneData(ObjectContainer phoneData) {
		super();
		this.phoneData = phoneData;
	}

	/**
	 * @return the customerData
	 */
	public ObjectContainer getPhoneData() {
		return phoneData;
	}

	/**
	 * @param PhoneData the phoneData to set
	 */
	public void setPhoneData(ObjectContainer PhoneData) {
		this.phoneData = PhoneData;
	}

	@Override
	public boolean create(Phone pho) {
		if (exists("number", pho.getNumber())) {
			return false;
		}
		pho.setCreatedAt(LocalDateTime.now());
		pho.setUpdatedAt(LocalDateTime.now());
		if(pho.getUser()!=null) {
			phoneData.store(pho.getUser());
		}
		phoneData.store(pho);
		phoneData.commit();
		return true;
	}

	@Override
	public Phone<Phone> update(Phone pho) {
		Phone<Phone> currentPhone = findBy("id", pho.getId());
		LocalDateTime createdAt = currentPhone.getCreatedAt();

		BeanUtils.copyProperties(pho, currentPhone);
		currentPhone.setUpdatedAt(LocalDateTime.now());
		currentPhone.setCreatedAt(createdAt);

		phoneData.store(currentPhone);
		phoneData.commit();

		return currentPhone;
	}

	@Override
	public boolean delete(Phone pho) {
		try {
			phoneData.delete(pho);
			phoneData.commit();
			return true;
		} catch (Exception error) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAll() {
		for(Phone<Phone>phone : findAll()) {
			phoneData.delete(phone);
			phoneData.commit();
		}
	}

	@Override
	public boolean exists(String key, String value) {
		return findBy(key, value) != null;
	}

	@Override
	public Phone<Phone> findBy(String key, String value) {
		Query query = phoneData.query();
		query.constrain(Phone.class);
		query.descend(key).constrain(value).equal();
		ObjectSet<Phone> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	@Override
	public ObjectSet<Phone> findAll() {
		Query query = phoneData.query();
		query.constrain(Phone.class);
		return query.execute();
	}
	
	@Override
	public ObjectSet<Phone> findAllBy(String key, String value) {
		Query query = phoneData.query();
		query.constrain(Phone.class);
		query.descend(key).constrain(value).equal();
		return query.execute();
	}

	@Override
	public boolean closeConnection(ObjectContainer conexao) {
		boolean closed = false;
		if (phoneData.close()) {
			closed= true;
		};
		return closed;
	}
}