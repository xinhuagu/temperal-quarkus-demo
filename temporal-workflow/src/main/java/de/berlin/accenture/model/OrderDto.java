package de.berlin.accenture.model;

import lombok.Data;

@Data
public class OrderDto {

  public enum Status {
    NEW,
    CREATED,
    CANCELED,
    SHIPPING;
  }

  private String id;
  private String product;
  private Integer amount;
  private Status status;

}