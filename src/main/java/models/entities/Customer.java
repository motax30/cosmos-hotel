package models.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	/* UUID */
	@NonNull private String id = Generators.timeBasedGenerator().generate().toString();

	@NonNull private String cpfNumber;
	@NonNull private String name;
	private String email;
	@NonNull private List<Phone<Customer>> phones;
	@NonNull private Address address;

	private String notes;
	private LocalDateTime birthday;

	@NonNull private LocalDateTime createdAt;
	@NonNull private LocalDateTime updatedAt;
	
	
}
