package edu.miu.cs590.bookingservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class EmailDto implements Serializable {

    private String from;
    private String to;
    private String subject;
    private String message;
}
