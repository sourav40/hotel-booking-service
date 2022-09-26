package edu.miu.cs590.bookingservice.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BookingUtil {

    public static Double calculateTotalAmount(Double rate, Integer noOfRooms, Double tax, LocalDateTime checkIn, LocalDateTime checkOut){
        long days = ChronoUnit.DAYS.between(checkIn,checkOut);
        if(days==0 && checkIn.isBefore(checkOut)){
            days=1;
        } else if (checkIn.plusDays(days).isBefore(checkOut)) {
            days++;
        }
        return rate*days*noOfRooms*(100+tax)/100;
    }
}
