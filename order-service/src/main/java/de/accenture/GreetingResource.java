package de.accenture;

import de.berlin.accenture.OrderDto;
import de.berlin.accenture.OrderWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Path("/order")
public class GreetingResource {

  @Inject
  WorkflowClient workflowClient;

  @POST
  public Response ordering(OrderDto order) {
    log.info("incoming order: " + order.toString());
    WorkflowOptions options = WorkflowOptions.newBuilder()
                                             .setTaskQueue(OrderWorkflow.ORDER_TASK_QUEUE)
                                             .setWorkflowId("Order-" + order.getId())
                                             .build();
    OrderWorkflow orderWorkflow = workflowClient.newWorkflowStub(OrderWorkflow.class, options);
    WorkflowClient.start(orderWorkflow::startOrdering, order);

    return Response.ok("order process starts")
                                                 .build();
  }
}