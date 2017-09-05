package models.entities;

import lombok.Data;
import models.enumerates.PhoneType;
import models.enumerates.ReceptionistType;

@Data
public class Phone<T> {
	private Enum<PhoneType> type;
	private int number;
	private T user;
	private ReceptionistType userType;
	
	public Phone(PhoneType type, int number) {
		this.type = type;
		this.number = number;
	}

}
