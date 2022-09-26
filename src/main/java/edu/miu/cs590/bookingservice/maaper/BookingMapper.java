package edu.miu.cs590.bookingservice.maaper;

import edu.miu.cs590.bookingservice.dto.BookingCustomerDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingDashboardDto;
import edu.miu.cs590.bookingservice.dto.BookingResponseDto;
import edu.miu.cs590.bookingservice.dto.BookingRequestDto;
import edu.miu.cs590.bookingservice.entity.BookingDetail;
import edu.miu.cs590.bookingservice.enums.BillingStatus;
import edu.miu.cs590.bookingservice.enums.BookingStatus;
import edu.miu.cs590.bookingservice.util.BookingUtil;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mappings({
            @Mapping(source = "rate", target = "billingDetail.rate"),
            @Mapping(source = "tax", target = "billingDetail.tax"),
            @Mapping(source = "noOfRooms", target = "billingDetail.noOfRooms"),
    })
    BookingDetail bookingRequestDtoToEntity(BookingRequestDto bookingRequestDto);

    @Mappings({
            @Mapping(target = "rate", source = "billingDetail.rate"),
            @Mapping(target = "tax", source = "billingDetail.tax"),
            @Mapping(target = "noOfRooms", source = "billingDetail.noOfRooms"),

    })
    BookingResponseDto entityToBookingResponseDto(BookingDetail bookingDetail);

    @Mappings({
            @Mapping(target = "rate", source = "billingDetail.rate"),
            @Mapping(target = "tax", source = "billingDetail.tax"),
            @Mapping(target = "noOfRooms", source = "billingDetail.noOfRooms"),
            @Mapping(target = "billingStatus", source = "billingDetail.billingStatus"),
            @Mapping(target = "paymentType", source = "billingDetail.paymentType"),
            @Mapping(target = "bookingDateTime", source = "bookingPlacedDate"),
    })
    BookingDashboardDto entityToDashboardDto(BookingDetail bookingDetail);

    @Mappings({
            @Mapping(target = "rate", source = "billingDetail.rate"),
            @Mapping(target = "tax", source = "billingDetail.tax"),
            @Mapping(target = "noOfRooms", source = "billingDetail.noOfRooms"),
            @Mapping(target = "billingStatus", source = "billingDetail.billingStatus"),
            @Mapping(target = "paymentType", source = "billingDetail.paymentType"),
            @Mapping(target = "bookingDateTime", source = "bookingPlacedDate"),
    })
    BookingCustomerDashboardDto entityToCustomerDashboardDto(BookingDetail bookingDetail);
    @AfterMapping
    default void setBookingStatus(@MappingTarget BookingDetail bookingDetail) {
       bookingDetail.setBookingStatus(BookingStatus.PENDING);
       bookingDetail.getBillingDetail().setBillingStatus(BillingStatus.PENDING);
    }

    @AfterMapping
    default void setTotalValue(BookingDetail bookingDetail,@MappingTarget BookingResponseDto bookingResponseDto){
        bookingResponseDto.setTotal(BookingUtil.calculateTotalAmount(bookingResponseDto.getRate(), bookingResponseDto.getNoOfRooms(), bookingResponseDto.getTax(), bookingDetail.getCheckInDateTime(), bookingDetail.getCheckOutDateTime()));
    }
    @AfterMapping
    default void setTotalValue(BookingDetail bookingDetail,@MappingTarget BookingDashboardDto bookingDashboardDto){
        bookingDashboardDto.setTotal(BookingUtil.calculateTotalAmount(bookingDashboardDto.getRate(), bookingDashboardDto.getNoOfRooms(), bookingDashboardDto.getTax(), bookingDetail.getCheckInDateTime(), bookingDetail.getCheckOutDateTime()));
    }

    @AfterMapping
    default void setTotalValue(BookingDetail bookingDetail,@MappingTarget BookingCustomerDashboardDto bookingCustomerDashboardDto){
        bookingCustomerDashboardDto.setTotal(BookingUtil.calculateTotalAmount(bookingCustomerDashboardDto.getRate(), bookingCustomerDashboardDto.getNoOfRooms(), bookingCustomerDashboardDto.getTax(), bookingDetail.getCheckInDateTime(), bookingDetail.getCheckOutDateTime()));
    }
    List<BookingDashboardDto> entityListToDashboardDtoList(List<BookingDetail> bookingDetails);


    List<BookingCustomerDashboardDto> entityListToCustomerDashboardDtoList(List<BookingDetail> bookingDetails);
}
