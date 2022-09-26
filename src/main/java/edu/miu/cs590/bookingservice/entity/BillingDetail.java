package edu.miu.cs590.bookingservice.entity;


import edu.miu.cs590.bookingservice.enums.BillingStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentType;

    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    private Integer noOfRooms;
    private Double rate;
    private Double tax;
}
