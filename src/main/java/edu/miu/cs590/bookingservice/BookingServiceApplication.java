package edu.miu.cs590.bookingservice;

import edu.miu.cs590.bookingservice.kafka.consumer.PaymentConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingServiceApplication implements CommandLineRunner {

    @Autowired
    private PaymentConsumer paymentConsumer;

    public static void main(String[] args) {
        new SpringApplicationBuilder(BookingServiceApplication.class)
                .sources(BookingServiceApplication.class)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        paymentConsumer.consumeMessage();
    }
}
