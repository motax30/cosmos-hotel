package models.entities;

import com.fasterxml.uuid.Generators;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.enumerates.ReceptionistType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Receptionist {
	/* UUID */
    private String id = Generators.timeBasedGenerator().generate().toString();

	@SerializedName("user_name")
	private String userName;

	private String email;
	private String password;

	private ReceptionistType userType;
	
	private String name;
}