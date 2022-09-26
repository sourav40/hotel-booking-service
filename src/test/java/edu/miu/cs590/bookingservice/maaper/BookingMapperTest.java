package edu.miu.cs590.bookingservice.maaper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs590.bookingservice.dto.BookingCustomerDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingRequestDto;
import edu.miu.cs590.bookingservice.dto.BookingResponseDto;
import edu.miu.cs590.bookingservice.entity.BookingDetail;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDateTime;

@SpringBootTest
class BookingMapperTest {

    @Autowired
    private BookingMapper bookingMapper;

    @SneakyThrows
    @Test
    void bookingRequestDtoToEntity() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingRequestDto bookingRequestDto = objectMapper.readValue(new File("src/test/resources/bookingRequestDto.json"), BookingRequestDto.class);
        BookingDetail expectedBookingDetails = objectMapper.readValue(new File("src/test/resources/bookingDetailsSave.json"), BookingDetail.class);
        BookingDetail resultBookingDetails = bookingMapper.bookingRequestDtoToEntity(bookingRequestDto);
        Assertions.assertEquals(expectedBookingDetails,resultBookingDetails);
    }

    @SneakyThrows
    @Test
    void entityToBookingResponseDto() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingDetail bookingDetails = objectMapper.readValue(new File("src/test/resources/bookingDetailsSaveResponse.json"), BookingDetail.class);
        BookingResponseDto expectedDto = objectMapper.readValue(new File("src/test/resources/bookingResponseDto.json"), BookingResponseDto.class);
        BookingResponseDto responseDto = bookingMapper.entityToBookingResponseDto(bookingDetails);
        Assertions.assertEquals(expectedDto,responseDto);
    }

    @SneakyThrows
    @Test
    void entityToDashboardDto() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingDetail bookingDetails = objectMapper.readValue(new File("src/test/resources/bookingDetailsSaveResponse.json"), BookingDetail.class);
        bookingDetails.setBookingPlacedDate(LocalDateTime.of(2022,9,25,15,15,30));
        BookingDashboardDto expectedDto = objectMapper.readValue(new File("src/test/resources/bookingDashboardDto.json"), BookingDashboardDto.class);
        BookingDashboardDto responseDto = bookingMapper.entityToDashboardDto(bookingDetails);
        Assertions.assertEquals(expectedDto,responseDto);
    }

    @SneakyThrows
    @Test
    void entityToCustomerDashboardDto() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookingDetail bookingDetails = objectMapper.readValue(new File("src/test/resources/bookingDetailsSaveResponse.json"), BookingDetail.class);
        bookingDetails.setBookingPlacedDate(LocalDateTime.of(2022,9,25,15,15,30));
        BookingCustomerDashboardDto expectedDto = objectMapper.readValue(new File("src/test/resources/bookingCustomerDashboardDto.json"), BookingCustomerDashboardDto.class);
        BookingCustomerDashboardDto responseDto = bookingMapper.entityToCustomerDashboardDto(bookingDetails);
        Assertions.assertEquals(expectedDto,responseDto);
    }

    @Test
    void entityListToDashboardDtoList() {
    }

    @Test
    void entityListToCustomerDashboardDtoList() {
    }
}