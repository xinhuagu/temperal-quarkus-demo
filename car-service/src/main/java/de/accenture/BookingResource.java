package de.accenture;

import de.berlin.accenture.BookingWorkflow;
import de.berlin.accenture.model.BookingDTO;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/booking")
public class BookingResource {

  @Inject
  WorkflowClient workflowClient;

  @POST
  public String booking(BookingDTO booking) {
    log.info("incoming booking: " + booking.toString());

    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setTaskQueue(BookingWorkflow.CAR_SERVICE_TASK_QUEUE)
        .setWorkflowId("Booking-" + booking.getId())
        .build();
    BookingWorkflow bookingWorkflow = workflowClient.newWorkflowStub(BookingWorkflow.class, options);
    var excution = WorkflowClient.start(bookingWorkflow::startBooking, booking);
    var workflowId = excution.getWorkflowId();

    return "Workflow " + workflowId + " is started";
  }
}
