package models.entities;

import java.time.LocalDate;

public class Recepcionist {

	private String name;
	private int address_id;
	private LocalDate bithday;
	
	public Recepcionist(String name, int address_id, LocalDate bithday) {
		this.name = name;
		this.address_id = address_id;
		this.bithday = bithday;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public LocalDate getBithday() {
		return bithday;
	}
	public void setBithday(LocalDate bithday) {
		this.bithday = bithday;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + address_id;
		result = prime * result + ((bithday == null) ? 0 : bithday.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recepcionist other = (Recepcionist) obj;
		if (address_id != other.address_id)
			return false;
		if (bithday == null) {
			if (other.bithday != null)
				return false;
		} else if (!bithday.equals(other.bithday))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
