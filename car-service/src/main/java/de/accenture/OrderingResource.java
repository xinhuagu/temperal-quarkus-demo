package de.accenture;

import de.berlin.accenture.OrderWorkflow;
import de.berlin.accenture.model.OrderDto;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Path("/order")
public class OrderingResource {

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

//    var result = orderWorkflow.ordering();
    return Response.ok("order process starts").entity(order)
                                                 .build();
  }
}