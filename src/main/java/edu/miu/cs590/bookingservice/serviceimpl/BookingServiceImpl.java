package edu.miu.cs590.bookingservice.serviceimpl;

import edu.miu.cs590.bookingservice.dto.BookingCustomerDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingResponseDto;
import edu.miu.cs590.bookingservice.dto.BookingRequestDto;
import edu.miu.cs590.bookingservice.maaper.BookingMapper;
import edu.miu.cs590.bookingservice.repository.BookingRepository;
import edu.miu.cs590.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingMapper bookingMapper;
    private BookingRepository bookingRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public BookingResponseDto processBooking(BookingRequestDto bookingRequestDto) {
        return bookingMapper.entityToBookingResponseDto(bookingRepository.save(bookingMapper.bookingRequestDtoToEntity(bookingRequestDto)));
    }

    @Override
    public List<BookingDashboardDto> getBookingDetailsByHotelId(String hotelId, Pageable pageable) {
        return bookingMapper.entityListToDashboardDtoList(bookingRepository.findAllByHotelId(hotelId,pageable));
    }

    @Override
    public List<BookingCustomerDashboardDto> getBookingDetailsByCustomerId(String customerId,Pageable pageable) {
        return bookingMapper.entityListToCustomerDashboardDtoList(bookingRepository.findAllByCustomerId(customerId,pageable));
    }
}
