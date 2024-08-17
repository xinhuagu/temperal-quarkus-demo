package de.berlin.accenture;

import de.berlin.accenture.activity.CarBookingActivity;
import de.berlin.accenture.activity.HotelBookingAcitivity;
import de.berlin.accenture.activity.FlightBookingActivity;
import de.berlin.accenture.model.BookingDTO;
import de.berlin.accenture.model.BookingResultDTO;
import de.berlin.accenture.model.BookingResultDTO.Status;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import org.slf4j.Logger;

public class BookingWorkflowImpl implements BookingWorkflow {

  private final RetryOptions retryOptions = RetryOptions.newBuilder()
      .setInitialInterval(Duration.ofSeconds(2))
      .setMaximumAttempts(1)
      .build();

  private final ActivityOptions carBookingActivityOptions = ActivityOptions.newBuilder()
      .setStartToCloseTimeout(
          Duration.ofSeconds(120))
      .setTaskQueue(
          CAR_SERVICE_TASK_QUEUE)
      .setRetryOptions(retryOptions)
      .build();

  private final ActivityOptions hotelBookingActivityOptions = ActivityOptions.newBuilder()
      .setStartToCloseTimeout(
          Duration.ofSeconds(120))
      .setTaskQueue(
          HOTEL_SERVICE_TASK_QUEUE)
      .setRetryOptions(
          retryOptions)
      .build();

  private final ActivityOptions flightBookingActivityOptions = ActivityOptions.newBuilder()
      .setStartToCloseTimeout(
          Duration.ofSeconds(
              120))
      .setTaskQueue(
          FLIGHT_SERVICE_TASK_QUEUE)
      .setRetryOptions(
          retryOptions)
      .build();

  private final CarBookingActivity carBookingActivity = Workflow.newActivityStub(CarBookingActivity.class,
      carBookingActivityOptions);

  private final HotelBookingAcitivity hotelBookingAcitivity = Workflow.newActivityStub(
      HotelBookingAcitivity.class, hotelBookingActivityOptions);

  private final FlightBookingActivity flightBookingActivity = Workflow.newActivityStub(
      FlightBookingActivity.class, flightBookingActivityOptions);

  Logger logger = Workflow.getLogger(this.getClass());

  @Override
  public BookingResultDTO startBooking(BookingDTO booking) {

    var workflowId = Workflow.getInfo()
        .getWorkflowId();

    logger.info("Workflow {} is starting", workflowId);

    Saga saga = new Saga(new Saga.Options.Builder().setParallelCompensation(false)
        .build());

    try {

      booking = carBookingActivity.bookCar(booking);
      saga.addCompensation(carBookingActivity::cancelCar, booking);

      booking = hotelBookingAcitivity.bookHotel(booking);
      saga.addCompensation(hotelBookingAcitivity::cancelHotel, booking);

      booking = flightBookingActivity.bookFlight(booking);

      logger.info("Workflow {} is finished", workflowId);

      return BookingResultDTO.builder().status(Status.SUCCESS).bookingId(booking.getId()).build();

    } catch (ActivityFailure activityFailure) {
      Workflow.newDetachedCancellationScope(() -> saga.compensate()).run();
      var result = BookingResultDTO.builder()
          .bookingId(booking.getId())
          .status(Status.FAILED).build();
      return result;
    }

  }

}
