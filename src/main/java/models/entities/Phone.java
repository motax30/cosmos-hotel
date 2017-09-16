package models.entities;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Phone<T> {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();

	private String type;
	private String number;
	private T user;

	public Phone(String type, String number) {
		this.type = type;
		this.number = number;
	}

}
