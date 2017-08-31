package models.entities;

import com.fasterxml.uuid.Generators;

import java.util.List;

public class Customer {
	private String id;
	private String cpfNumber;
	private String name;
	private List<Phone<Customer>> phones;
	private List<Address> address;
//	private String classification;
	private String notes;
	
	public Customer() {
		this.id = Generators.timeBasedGenerator().generate().toString();
	}

	public Customer(String cpfNumber, String name, List<Phone<Customer>> phones, List<Address> address, String notes) {
		super();
        this.id = Generators.timeBasedGenerator().generate().toString();
		this.cpfNumber = cpfNumber;
		this.name = name;
		this.phones = phones;
		this.address = address;
		this.notes = notes;
	}

	public Customer(String name) {
		this.name = name;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getCpfNumber() {
		return cpfNumber;
	}

	public void setCpfNumber(String cpfNumber) {
		this.cpfNumber = cpfNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Phone<Customer>> getPhones() {
		return phones;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	public void addPhone(Phone phone) {
		phones.add(phone);
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((cpfNumber == null) ? 0 : cpfNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (cpfNumber == null) {
			if (other.cpfNumber != null)
				return false;
		} else if (!cpfNumber.equals(other.cpfNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		return true;
	}
}
