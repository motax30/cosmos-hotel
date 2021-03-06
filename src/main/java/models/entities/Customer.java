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
	private String id = Generators.timeBasedGenerator().generate().toString();

	private String cpfNumber;
	private String name;
	private String email;
	private List<Phone<Customer>> phones;
	private Address address;

	private String notes;
	private LocalDateTime birthday;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
}
