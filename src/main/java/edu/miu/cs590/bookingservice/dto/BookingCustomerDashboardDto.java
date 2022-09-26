package edu.miu.cs590.bookingservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingCustomerDashboardDto {
    private Long id;
    private String bookingCode;
    private String hotelName;
    private String roomTypeName;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    private LocalDateTime bookingDateTime;
    private String cancellationPolicy;
    private String bookingStatus;
    private String billingStatus;
    private String paymentType;
    private Double rate;
    private Integer noOfRooms;
    private Double tax;
    private Double total;
}
