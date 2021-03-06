package models.entities;

import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.enumerates.PhoneType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Phone<T> {
	/* UUID */
	private String id = Generators.timeBasedGenerator().generate().toString();

	private PhoneType type;
	private String number;
	private Object user;
	private LocalDateTime createdAt;
	private LocalDateTime UpdatedAt;
}
