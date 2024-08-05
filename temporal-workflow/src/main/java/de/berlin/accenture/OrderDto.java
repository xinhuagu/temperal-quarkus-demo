package de.berlin.accenture;

import lombok.Data;

@Data
public class OrderDto {

  public enum Status {
    CREATED,
    CANCELED,
    PAID,
    SHIPPED;
  }

  private String id;
  private String product;
  private Integer amount;
  private Status status;

}