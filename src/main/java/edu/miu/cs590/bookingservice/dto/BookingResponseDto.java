package edu.miu.cs590.bookingservice.dto;

import lombok.Data;

@Data
public class BookingResponseDto {

    private String bookingCode;
    private Integer noOfRooms;
    private Double rate;
    private Double tax;
    private Double total;
}
