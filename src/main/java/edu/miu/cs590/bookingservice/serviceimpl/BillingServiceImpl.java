package edu.miu.cs590.bookingservice.serviceimpl;

import edu.miu.cs590.bookingservice.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class BillingServiceImpl implements BillingService {
}
