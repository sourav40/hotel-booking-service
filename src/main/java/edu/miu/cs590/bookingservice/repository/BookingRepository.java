package edu.miu.cs590.bookingservice.repository;

import edu.miu.cs590.bookingservice.entity.BookingDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingDetail,Long>{
    List<BookingDetail> findAllByHotelId(String hotelId, Pageable pageable);
    List<BookingDetail> findAllByCustomerId(String customerId, Pageable pageable);
}
