package de.berlin.accenture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

  public enum Status {
    NEW,
    CAR_BOOKED,
    CAR_CANCELED,
    HOTEL_BOOKED,
    HOTEL_CANCELED,
    FLIGHT_BOOKED,
    FAILED,
    SUCCESS;
  }

  private String id;
  private Status status;

}
