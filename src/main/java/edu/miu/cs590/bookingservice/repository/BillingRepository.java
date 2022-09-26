package edu.miu.cs590.bookingservice.repository;

import edu.miu.cs590.bookingservice.entity.BillingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<BillingDetail,Long> {
}
