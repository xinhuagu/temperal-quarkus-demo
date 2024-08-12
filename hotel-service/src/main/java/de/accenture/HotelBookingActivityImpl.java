package de.accenture;

import de.berlin.accenture.activity.HotelBookingAcitivity;
import de.berlin.accenture.model.BookingDTO;
import de.berlin.accenture.model.BookingDTO.Status;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class HotelBookingActivityImpl implements HotelBookingAcitivity {

  @Override
  public BookingDTO bookHotel(BookingDTO bookingDTO) {
    bookingDTO.setStatus(Status.HOTEL_BOOKED);
    log.info("Step 2 => Booking Id: {}, Status: {}", bookingDTO.getId(), bookingDTO.getStatus());
    return bookingDTO;
  }

  @Override
  public BookingDTO cancelHotel(BookingDTO bookingDTO) {
    bookingDTO.setStatus(Status.HOTEL_CANCELED);
    log.info("Compensation => Booking Id: {} , Status -> {}", bookingDTO.getId(), bookingDTO.getStatus());
    return bookingDTO;
  }
}
