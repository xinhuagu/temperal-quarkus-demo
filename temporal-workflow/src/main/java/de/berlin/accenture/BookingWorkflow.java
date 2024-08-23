package de.berlin.accenture;

import de.berlin.accenture.model.BookingDTO;
import de.berlin.accenture.model.BookingResultDTO;
import io.temporal.workflow.UpdateMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BookingWorkflow {
  final static String CAR_SERVICE_TASK_QUEUE = "carServiceTaskQueue";
  final static String HOTEL_SERVICE_TASK_QUEUE = "hotelServiceTaskQueue";
  final static String FLIGHT_SERVICE_TASK_QUEUE = "flightServiceTaskQueue";

  @WorkflowMethod(name = "Booking")
  BookingResultDTO startBooking(BookingDTO order);

  @UpdateMethod
  BookingResultDTO booking();

}