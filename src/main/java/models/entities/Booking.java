package models.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.uuid.Generators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import models.enumerates.PaymentOptions;
import models.enumerates.StatusBooking;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Booking {
	 
	private String id = Generators.timeBasedGenerator().generate().toString();
	 
	 private Customer customer;
	 private Accommodation accommodation;
	 private Receptionist recepcionist;
	 private double dayleValue;
	 private String status;
	 private LocalDateTime checkInDate;
	 private LocalDateTime checkOutDate;
	 private LocalDateTime BookingInitialDate;
	 private LocalDateTime BookingFinalDate;
	 private double paymentValue;
	 private String conditionPayment;
	 private double paymentMade;
	 private LocalDateTime createdAt;
	 private LocalDateTime updatedAt;
	 
	 public Booking(Customer customer, Accommodation accommodation, Receptionist recepcionist, double dailyValue,
				String status, LocalDateTime checkInDate, LocalDateTime checkOutDate, LocalDateTime bookinginitialdate,
				LocalDateTime bookingfinaldate, double paymentValue, String conditionPayment, double paymentMade, LocalDateTime createdAt,
				LocalDateTime updateAt) {
		 this.customer = customer;
		 this.accommodation = accommodation;
		 this.recepcionist = recepcionist;
		 this.dayleValue = dayleValue;
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