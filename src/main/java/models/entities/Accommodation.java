package models.entities;

import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.enumerates.AccommodationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Accommodation {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();
	private AccommodationType type;

	private double dailyPrice = 0;
	private int numberOfBeds = 0;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}