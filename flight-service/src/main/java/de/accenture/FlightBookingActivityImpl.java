package de.accenture;

import de.berlin.accenture.activity.FlightBookingActivity;
import de.berlin.accenture.model.BookingDTO;
import de.berlin.accenture.model.BookingDTO.Status;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class FlightBookingActivityImpl implements FlightBookingActivity {

  @Override
  public BookingDTO bookFlight(BookingDTO bookingDTO) {
    bookingDTO.setStatus(Status.FLIGHT_BOOKED);
    log.info("Step 3 => Order Id {}, Status: {}", bookingDTO.getId(), bookingDTO.getStatus());
    return bookingDTO;
  }

}