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
public class Accommodation {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();
	
	private String typeAccommodation;
	private AccommodationTypeInformations accommodationTypeInformations;
}
