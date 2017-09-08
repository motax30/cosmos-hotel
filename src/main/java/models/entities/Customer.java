package models.entities;

import com.fasterxml.uuid.Generators;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();

	@SerializedName("cpf_number")
	private String cpfNumber;

	private String name;

	private List<Phone<Customer>> phones;
	private List<Address> address;

	private String notes;
	private LocalDate birthday;
}
