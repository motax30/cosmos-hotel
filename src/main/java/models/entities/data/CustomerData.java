package models.entities.data;

import models.entities.Customer;
import models.entities.Phone;

public class CustomerData extends DatableImplementation<Customer> {
	/*
	 * Public Methods
	 */
	@Override
	public boolean create(Customer customer) {
		if (exists("cpfNumber", customer.getCpfNumber()) || exists("email", customer.getEmail())) {
			return false;
		}

		if (customer.getAddress() != null) {
			this.getObjectContainer().store(customer.getAddress());
		}

		if (customer.getPhones() != null) {
			for (Phone<Customer> phone : customer.getPhones()) {
				this.getObjectContainer().store(phone);
			}
		}

		return super.create(customer);
	}
}