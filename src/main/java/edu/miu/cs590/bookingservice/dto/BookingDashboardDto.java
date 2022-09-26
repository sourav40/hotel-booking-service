package edu.miu.cs590.bookingservice.dto;

import edu.miu.cs590.bookingservice.enums.BillingStatus;
import edu.miu.cs590.bookingservice.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDashboardDto {
    private Long id;
    private String hotelId;
    private String hotelName;
    private String roomId;
    private String roomTypeName;
    private String bookingCode;
    private String customerId;
    private String customerName;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    private LocalDateTime bookingDateTime;
    private String cancellationPolicy;
    private BookingStatus bookingStatus;
    private BillingStatus billingStatus;
    private String paymentType;
    private Integer noOfRooms;
    private Double rate;
    private Double tax;
    private Double total;
}
