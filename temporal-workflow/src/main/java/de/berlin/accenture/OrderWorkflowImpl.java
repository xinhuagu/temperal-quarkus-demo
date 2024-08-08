package de.berlin.accenture;

import de.berlin.accenture.activity.OrderActivity;
import de.berlin.accenture.activity.PaymentAcitivity;
import de.berlin.accenture.activity.ShipmentActivity;
import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderingResultDto;
import de.berlin.accenture.model.OrderingResultDto.Status;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.workflow.CompletablePromise;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import java.time.Duration;
import org.slf4j.Logger;

public class OrderWorkflowImpl implements OrderWorkflow {



  private final RetryOptions retryOptions = RetryOptions.newBuilder()
                                                        .setInitialInterval(Duration.ofSeconds(2))
                                                        .setMaximumAttempts(3)
                                                        .build();

  private final ActivityOptions orderActivityOptions = ActivityOptions.newBuilder()
                                                                      .setStartToCloseTimeout(
                                                                          Duration.ofSeconds(120))
                                                                      .setTaskQueue(
                                                                          ORDER_TASK_QUEUE)
                                                                      .setRetryOptions(retryOptions)
                                                                      .build();

  private final ActivityOptions paymentActivityOptions = ActivityOptions.newBuilder()
                                                                        .setStartToCloseTimeout(
                                                                            Duration.ofSeconds(120))
                                                                        .setTaskQueue(
                                                                            PAYMENT_TASK_QUEUE)
                                                                        .setRetryOptions(
                                                                            retryOptions)
                                                                        .build();

  private final ActivityOptions shipmentActivityOptions = ActivityOptions.newBuilder()
                                                                         .setStartToCloseTimeout(
                                                                             Duration.ofSeconds(
                                                                                 120))
                                                                         .setTaskQueue(
                                                                             SHIPMENT_TASK_QUEUE)
                                                                         .setRetryOptions(
                                                                             retryOptions)
                                                                         .build();

  private final OrderActivity orderActivity = Workflow.newActivityStub(OrderActivity.class,
      orderActivityOptions);

  private final PaymentAcitivity paymentAcitivity = Workflow.newActivityStub(PaymentAcitivity.class,
      paymentActivityOptions);

  private final ShipmentActivity shipmentActivity = Workflow.newActivityStub(ShipmentActivity.class,
      shipmentActivityOptions);

  private final CompletablePromise<OrderingResultDto> ordering = Workflow.newPromise();

  Logger logger = Workflow.getLogger(this.getClass());

  @Override
  public void startOrdering(OrderDto order) {

    var workflowId = Workflow.getInfo()
                             .getWorkflowId();

    logger.info("Workflow {} is starting", workflowId);

    Saga saga = new Saga(new Saga.Options.Builder().setParallelCompensation(false)
                                                   .build());

    try {

      order = orderActivity.createOrder(order);
      saga.addCompensation(orderActivity::cancelOrder, order);


      order = paymentAcitivity.commitPayment(order);
      saga.addCompensation(paymentAcitivity::rollbackPayment, order);

      order = shipmentActivity.startShipment(order);

      ordering.complete(OrderingResultDto.builder()
                  .orderId(order.getId())
                  .status(Status.SUCCESS)
                   .build());

      logger.info("Workflow {} is finished", workflowId);

    } catch (ActivityFailure activityFailure) {
      Workflow.newDetachedCancellationScope(() -> saga.compensate()).run();
      var orderingResult = OrderingResultDto.builder()
                                            .orderId(order.getId())
                                            .status(Status.FAILED)
                                            .build();
      ordering.complete(orderingResult);
    }

  }

  @Override
  public OrderingResultDto ordering() {
    var or = ordering.get();
    logger.info("Result => Order id: {}, Status: {}", or.getOrderId(), or.getStatus());
    return or;
  }


}