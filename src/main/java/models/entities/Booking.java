package models.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import models.enumerates.PaymentOptions;
import models.enumerates.StatusBooking;

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
	 
	 public Booking(Customer customer, Accommodation accommodation, Receptionist recepcionist, double dailyValue,
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