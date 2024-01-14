package com.example.DeliveryApp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class ErrorDTO {
   private final int status;
   private final String type;
   private final String message;
}
