package models.entities.data;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.entities.Address;
import models.entities.Receptionist;
import settings.DatabaseServer;

@Data
@AllArgsConstructor
public class ReceptionistData {
	private ObjectContainer receptionistData;

	public ReceptionistData() {
		receptionistData = DatabaseServer.getServer().openClient();
	}

	/*
	 * Public Methods
	 */
	public boolean receptionistAdd(Receptionist receptionist) {
		if(!receptionistExists(receptionist.getName())) {
			if (receptionist.getAddress() != null) {
				receptionistData.store(receptionist.getAddress());
			}
			receptionistData.store(receptionist);
			receptionistData.commit();
			return true;
		}
		return false;
	}
	
	public boolean receptionistRemove(Receptionist receptionist) {
		if(receptionistByCpfExists(receptionist.getCpfNumber())) {
			receptionistData.delete(receptionist);
			receptionistData.commit();
			return true;
		}
		return false;
	}

	public boolean receptionistExists(String name) {
		return this.getReceptionistByReceptionistName(name) != null;
	}
	public boolean receptionistByCpfExists(String cpfNumber) {
		return this.getReceptionistByReceptionistCpf(cpfNumber) != null;
	}

	public Receptionist getReceptionistById(String id) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend("id").constrain(id).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}

	public Receptionist receptionistUpdate(Receptionist receptionist) {
		Receptionist currentReceptionist = getReceptionistById(receptionist.getId());
		currentReceptionist.setName(receptionist.getName());
		currentReceptionist.setNotes(receptionist.getNotes());
		receptionistData.store(currentReceptionist);
		receptionistData.commit();
		return currentReceptionist;
	}

	public Receptionist getReceptionistByReceptionistCpf(String cpfNumber) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend("cpfNumber").constrain(cpfNumber).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public Receptionist getReceptionistByReceptionistName(String name) {
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		query.descend("name").constrain(name).equal();
		ObjectSet<Receptionist> result = query.execute();
		return result.hasNext() ? result.next() : null;
	}
	
	public ObjectSet<Receptionist> getReceptionists(){
		Query query = receptionistData.query();
		query.constrain(Receptionist.class);
		return query.execute();
	}
}
