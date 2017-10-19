package models.entities;

import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Booking {
	 
	@NonNull private String id = Generators.timeBasedGenerator().generate().toString();
	 
	@NonNull private Customer customer;
	@NonNull private Accommodation accommodation;
	@NonNull private Receptionist recepcionist;
	@NonNull private String status;
	@NonNull  private LocalDateTime checkInDate;
	 private LocalDateTime checkOutDate;
	@NonNull private LocalDateTime BookingInitialDate;
	@NonNull private LocalDateTime BookingFinalDate;
	 private double paymentValue;
	 private String conditionPayment;
	 private double paymentMade;
	@NonNull private LocalDateTime createdAt;
	@NonNull private LocalDateTime updatedAt;
	 
	 public Booking(Customer customer, Accommodation accommodation, Receptionist recepcionist,
				String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime bookinginitialdate,
				LocalDateTime bookingfinaldate, double paymentValue, String conditionPayment, double paymentMade, LocalDateTime createdAt,
				LocalDateTime updateAt) {
		 this.customer = customer;
		 this.accommodation = accommodation;
		 this.recepcionist = recepcionist;
		 this.status = status;
		 this.checkInDate = checkInDate;
		 this.checkOutDate = checkOutDate;
		 this.BookingInitialDate = bookinginitialdate;
		 this.BookingFinalDate = bookingfinaldate;
		 this.paymentValue = paymentValue;
		 this.conditionPayment = conditionPayment;
		 this.paymentMade = paymentMade;
		 this.createdAt = createdAt;
		 this.updatedAt = updateAt;
	 }
}