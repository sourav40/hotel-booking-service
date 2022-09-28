package edu.miu.cs590.bookingservice.service;

import edu.miu.cs590.bookingservice.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {
    BookingResponseDto processBooking(BookingRequestDto bookingRequestDto);

    List<BookingDashboardDto> getBookingDetailsByHotelId(String hotelId, Pageable pageable);

    List<BookingCustomerDashboardDto> getBookingDetailsByCustomerId(String customerId,Pageable pageable);
    void updateBookingAndPaymentStatus(PaymentResponseDto paymentDto);

}
