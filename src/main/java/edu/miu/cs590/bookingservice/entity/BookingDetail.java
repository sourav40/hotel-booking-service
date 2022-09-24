package edu.miu.cs590.bookingservice.entity;

import edu.miu.cs590.bookingservice.enums.BookingStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID bookingCode;

    private UUID hotelId;
    private String hotelName;

    private UUID roomId;
    private String roomTypeName;

    private UUID customerId;
    private String customerName;

    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne
    private BillingDetail billingDetail;

    private String cancellationPolicy;

    @CreationTimestamp
    private LocalDateTime bookingPlacedDate;

    @UpdateTimestamp
    private LocalDateTime bookingUpdateDate;


    @PrePersist
    public void autofill() {
        this.setBookingCode(UUID.randomUUID());
    }
}
