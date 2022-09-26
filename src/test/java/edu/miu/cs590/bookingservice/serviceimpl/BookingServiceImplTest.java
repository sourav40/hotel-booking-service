package edu.miu.cs590.bookingservice.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs590.bookingservice.dto.BookingRequestDto;
import edu.miu.cs590.bookingservice.dto.BookingResponseDto;
import edu.miu.cs590.bookingservice.entity.BookingDetail;
import edu.miu.cs590.bookingservice.repository.BookingRepository;
import edu.miu.cs590.bookingservice.service.BookingService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;

@SpringBootTest
class BookingServiceImplTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void before(){
        objectMapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    @Test
    void processBooking() {
        BookingRequestDto bookingRequestDto = objectMapper.readValue(new File("src/test/resources/bookingRequestDto.json"), BookingRequestDto.class);
        BookingResponseDto expected = objectMapper.readValue(new File("src/test/resources/bookingResponseDto.json"), BookingResponseDto.class);
        BookingDetail bookingDetails= objectMapper.readValue(new File("src/test/resources/bookingDetailsSaveResponse.json"), BookingDetail.class);
        Mockito.when(bookingRepository.save(Mockito.any(BookingDetail.class))).thenReturn(bookingDetails);
        BookingResponseDto result = bookingService.processBooking(bookingRequestDto);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getBookingDetailsByHotelId() {
    }

    @Test
    void getBookingDetailsByCustomerId() {
    }
}