package models.entities;

import com.fasterxml.uuid.Generators;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
	/* UUID */
    private String id = Generators.timeBasedGenerator().generate().toString();

	private String street;
	private String number;
	private String neighborhood;

	@SerializedName("zip_code")
	private String zipCode;

	private String city;
	private String state;
	private String country;

	/* TODO: this constructor is being used only in tests */
    public Address(String street) {
        this.street = street;
    }
}
