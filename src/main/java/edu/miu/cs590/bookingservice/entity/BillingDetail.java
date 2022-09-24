package edu.miu.cs590.bookingservice.entity;


import edu.miu.cs590.bookingservice.enums.BillingStatus;
import edu.miu.cs590.bookingservice.enums.PaymentType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
public class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;

    private BigDecimal rate;
    private BigDecimal tax;
}
