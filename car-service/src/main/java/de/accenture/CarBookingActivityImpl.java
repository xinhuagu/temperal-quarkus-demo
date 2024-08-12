package de.accenture;

import de.berlin.accenture.activity.CarBookingActivity;
import de.berlin.accenture.model.BookingDTO;
import de.berlin.accenture.model.BookingDTO.Status;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CarBookingActivityImpl implements CarBookingActivity {
  @Override
  public BookingDTO bookCar(BookingDTO bookingDTO) {
    bookingDTO.setStatus(Status.CAR_BOOKED);
    log.info("Step 1 => Booking Id: {} , Status: {}", bookingDTO.getId(), bookingDTO.getStatus());
    return bookingDTO;
  }

  @Override
  public BookingDTO cancelCar(BookingDTO bookingDTO) {
    bookingDTO.setStatus(Status.CAR_CANCELED);
    log.info("Compensation => Booking Id: {} , Status -> {}", bookingDTO.getId(), bookingDTO.getStatus());
    return bookingDTO;
  }
}
