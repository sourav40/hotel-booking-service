package edu.miu.cs590.bookingservice.kafka.consumer;

import edu.miu.cs590.bookingservice.dto.PaymentResponseDto;
import edu.miu.cs590.bookingservice.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
@Service
public class PaymentConsumer {

    @Value("${kafka-server-url}")
    private String kafkaUrl;

    @Value("${kafka-server-port}")
    private String kafkaPort;

    @Autowired
    private BookingService bookingService;

    public void consumeMessage() {

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl + ":" + kafkaPort);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, " edu.miu.cs590.bookingservice.deserializer.PaymentResponseDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "email-payment-consumer-group"); // consumer needs to be in certain group
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // read for the first offset

        KafkaConsumer<String, PaymentResponseDto> consumer = new KafkaConsumer<>(properties);

        final Thread mainThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Detected a shutdown, let's exit by calling consumer.wakeup()...");
                consumer.wakeup();

                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            consumer.subscribe(Arrays.asList("payment_response"));

            while (true) {
                log.info("polling...");
                ConsumerRecords<String, PaymentResponseDto> records = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, PaymentResponseDto> record : records) {
                    log.info("Value: " + record.value() + "\n" +
                            "Partition: " + record.partition() + "\n" +
                            "Offset: " + record.offset() + "\n"
                    );
                    PaymentResponseDto paymentResponseDto = record.value();
                    log.info("Payment response ::" + paymentResponseDto);
                   bookingService.updateBookingAndPaymentStatus(paymentResponseDto);
                }
            }
        } catch (WakeupException e) {
            log.info("Wake up exception!!!");
            // this is an expected exception while trying to close the consumer
        } catch (Exception e) {
            log.error("Unexpected exception.");
        } finally {
            consumer.close();
            log.info("The consumer in now gracefully closed.");
        }
    }

}
