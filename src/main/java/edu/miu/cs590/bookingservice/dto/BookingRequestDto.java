package edu.miu.cs590.bookingservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.miu.cs590.bookingservice.util.StringToLocalDateTimeConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto{
    @NotBlank
    private String hotelId;
    @NotBlank
    private String hotelName;
    @NotBlank
    private String roomId;
    @NotBlank
    private String roomTypeName;
    @NotBlank
    private String customerId;
    @NotBlank
    private String customerName;
    @NotBlank
    @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime checkInDateTime;
    @NotBlank
    @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime checkOutDateTime;
    @NotBlank
    private String cancellationPolicy;
    @NotNull
    private Double rate;
    @NotNull
    private Integer noOfRooms;
    @NotNull
    private Double tax;
}
