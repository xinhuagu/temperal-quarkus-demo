package de.berlin.accenture.activity;

import de.berlin.accenture.model.BookingDTO;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface HotelBookingAcitivity {

  @ActivityMethod
  BookingDTO bookHotel(BookingDTO orderDto);

  @ActivityMethod
  BookingDTO cancelHotel(BookingDTO payment);

}