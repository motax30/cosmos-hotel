package models.entities;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.enumerates.TypeAccommodation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationTypeInformations {
	
	private String id = Generators.timeBasedGenerator().generate().toString();
	private String typeAccommodation;
	private double dailyPrice=0;
	private int numberBeds=0;
	
	public AccommodationTypeInformations(TypeAccommodation simples, Double daylePrice, int beds) {
		this.typeAccommodation = simples.toString();
		this.dailyPrice = daylePrice;
		this.numberBeds = beds;
	}
}