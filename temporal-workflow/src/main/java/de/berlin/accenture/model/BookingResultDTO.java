package de.berlin.accenture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResultDTO {
  public enum Status {
    SUCCESS,
    FAILED;
  }

  private String bookingId;
  private Status status;
}
