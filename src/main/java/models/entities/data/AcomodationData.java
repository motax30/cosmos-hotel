package models.entities.data;

import com.db4o.ObjectContainer;

import models.entities.Acomodation;
import settings.DatabaseServer;

public class AcomodationData {
	ObjectContainer acomodationData;
	/*
	 * Constructors	
	 */
	public AcomodationData() {
		acomodationData = DatabaseServer.getServer().openClient();
	}
	
	public AcomodationData(ObjectContainer acomodationData) {
		super();
		this.acomodationData = acomodationData;
	}
	
	/**
	 * @return the acomodationData
	 */
	public ObjectContainer acomodationData() {
		return acomodationData;
	}
	
	/**
	 * @param acomodationData the acomodationData to set
	 */
	public void setCustomerData(ObjectContainer acomodationData) {
		this.acomodationData = acomodationData;
	}
	
	public boolean acomodationAdd(Acomodation acomodation) {
		return false;
//		if(!acomodationExists(acomodation.getAcomodationType())) {
//			
//		}
//		return true;
	}
}
