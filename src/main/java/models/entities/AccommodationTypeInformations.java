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
public class AccommodationTypeInformations {
	private double dailyPrice=0;
	private int numberBeds=0;
	private String idAccommodation;
}