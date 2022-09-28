package edu.miu.cs590.bookingservice.deserializer;

import edu.miu.cs590.bookingservice.dto.PaymentResponseDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class PaymentResponseDeserializer implements Deserializer<PaymentResponseDto> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Nothing to configure
    }

    @Override
    public PaymentResponseDto deserialize(String topic, byte[] data) {

        try {
            if (data == null) {
                System.out.println("Null received at deserialize");
                return null;
            }

            ByteBuffer buf = ByteBuffer.wrap(data);

            int sizeOfBookingCode = buf.getInt();
            byte[] bookingCodeBytes = new byte[sizeOfBookingCode];
            buf.get(bookingCodeBytes);
            String deserializedBookingCode = new String(bookingCodeBytes, encoding);

            int sizeOfPaymentStatus = buf.getInt();
            byte[] paymentStatusBytes = new byte[sizeOfPaymentStatus];
            buf.get(paymentStatusBytes);
            String deserializedPaymentStatus = new String(paymentStatusBytes, encoding);

            return new PaymentResponseDto(deserializedBookingCode, deserializedPaymentStatus);

        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Supplier");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}

