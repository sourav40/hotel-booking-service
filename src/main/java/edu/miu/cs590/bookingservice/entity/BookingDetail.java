package edu.miu.cs590.bookingservice.entity;

import edu.miu.cs590.bookingservice.enums.BookingStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingCode;

    private String hotelId;
    private String hotelName;

    private String roomId;
    private String roomTypeName;

    private String customerId;
    private String customerName;

    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    private BillingDetail billingDetail;

    private String cancellationPolicy;

    @CreationTimestamp
    private LocalDateTime bookingPlacedDate;

    @UpdateTimestamp
    private LocalDateTime bookingUpdateDate;

    @PrePersist
    public void autofill() {
        this.setBookingCode(UUID.randomUUID().toString().replace("-","").toUpperCase());
    }
}
