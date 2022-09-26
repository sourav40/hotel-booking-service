package edu.miu.cs590.bookingservice.controller;

import edu.miu.cs590.bookingservice.dto.BookingCustomerDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingResponseDto;
import edu.miu.cs590.bookingservice.dto.BookingRequestDto;
import edu.miu.cs590.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    @PostMapping
    BookingResponseDto processBooking(@RequestBody @Valid BookingRequestDto bookingRequestDto){
        return bookingService.processBooking(bookingRequestDto);
    }

    @GetMapping("/hotels/{hotelId}")
    List<BookingDashboardDto> getBookingDetailByHotelId(@PathVariable String hotelId,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer size,
                                                        @RequestParam(required = false,defaultValue = "ASC") String direction,
                                                        @RequestParam(required = false, defaultValue = "id") String orderBy
                                                        ){
        Pageable pageable;

        if(direction.equals("DESC")) pageable = PageRequest.of(page, size, Sort.Direction.DESC,orderBy);
        else pageable = PageRequest.of(page, size, Sort.Direction.ASC,orderBy);
        return bookingService.getBookingDetailsByHotelId(hotelId,pageable);
    }

    @GetMapping("/customers/{customerId}")
    List<BookingCustomerDashboardDto> getBookingDetailsForCustomer(@PathVariable String customerId,
                                                                   @RequestParam Integer page,
                                                                   @RequestParam Integer size,
                                                                   @RequestParam(required = false,defaultValue = "ASC") String direction,
                                                                   @RequestParam(required = false, defaultValue = "id") String orderBy
    ){
        Pageable pageable;
        if(direction.equals("DESC")) pageable = PageRequest.of(page, size, Sort.Direction.DESC,orderBy);
        else pageable = PageRequest.of(page, size, Sort.Direction.ASC,orderBy);
        return bookingService.getBookingDetailsByCustomerId(customerId,pageable);
    }

}
