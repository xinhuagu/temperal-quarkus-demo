package de.berlin.accenture.activity;

import de.berlin.accenture.model.BookingDTO;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface CarBookingActivity {

  @ActivityMethod
  BookingDTO bookCar(BookingDTO order);

  @ActivityMethod
  BookingDTO cancelCar(BookingDTO order);

}