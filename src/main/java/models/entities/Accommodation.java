package models.entities;

import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Accommodation {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();
	
	private AccommodationTypeInformations accommodationTypeInformations;
	private boolean isOcupied;
	private LocalDateTime createdAt;
	private LocalDateTime UpdatedAt;
	
	public Accommodation(AccommodationTypeInformations accommodationTypeInformations,
			boolean isOcupied, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		//this.typeAccommodation = typeAccommodation;
		this.accommodationTypeInformations = accommodationTypeInformations;
		this.isOcupied = isOcupied;
		this.createdAt = createdAt;
		UpdatedAt = updatedAt;
	}
	
	
}