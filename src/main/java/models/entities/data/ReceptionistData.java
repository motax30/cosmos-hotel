package models.entities.data;

import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import models.entities.Receptionist;

@AllArgsConstructor
public class ReceptionistData extends DatableImplementation<Receptionist> {

	public boolean receptionistLogin(String userName, String password) {
		Query query = getObjectContainer().query();
		query.constrain(Receptionist.class);
		Constraint constrain = query.descend("userName").constrain(userName);
		query.descend("password").constrain(password).and(constrain);
		
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext();
	}

	@Override
	public boolean create(Receptionist receptionist) {
		return !exists("userName", receptionist.getUserName())
				&& !exists("password", receptionist.getPassword())
				&& super.create(receptionist);
	}
}