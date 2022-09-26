package edu.miu.cs590.bookingservice.util;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {
    public LocalDateTime convert(final String value) {
        return LocalDateTime.parse(value,  DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
