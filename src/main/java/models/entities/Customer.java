package models.entities;

import java.util.List;

public class Customer {
	private int cpf_number;
	private String name;
	private List<Phone> phones;
//	private String classification;
	private String notes;
	
	public Customer(int id, String name, List<Phone> phones, String notes) {
		super();
		this.name = name;
		this.phones = phones;
		this.notes = notes;
	}
	
	public int getCpf() {
		return cpf_number;
	}

	public void setCpf(int cpf_number) {
		this.cpf_number = cpf_number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Phone> getPhones() {
		return phones;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpf_number;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (cpf_number != other.cpf_number)
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
