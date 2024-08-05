package de.berlin.accenture;

import lombok.Data;

@Data
public class OrderDto {
  private String id;
  private String product;
  private Integer amount;
  private boolean paid;

}