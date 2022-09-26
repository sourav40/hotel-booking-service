package edu.miu.cs590.bookingservice.serviceimpl;

import edu.miu.cs590.bookingservice.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceImplTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    void processBooking() {
    }

    @Test
    void getBookingDetailsByHotelId() {
    }

    @Test
    void getBookingDetailsByCustomerId() {
    }
}