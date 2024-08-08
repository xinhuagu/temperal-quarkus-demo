package de.berlin.accenture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

  public enum Status {
    NEW,
    CREATED,
    PAID,
    PAYMENT_ROLLBACK,
    CANCELED,
    SHIPPING,
    SHIPPING_FAILED,
    SHIPPED,
    FINISHED;
  }

  private String id;
  private String product;
  private Integer amount;
  private Status status;

}