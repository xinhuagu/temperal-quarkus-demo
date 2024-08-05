package de.berlin.accenture;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

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

  @Override
  public void startOrdering(OrderDto order) {

    var workflowId = Workflow.getInfo()
                             .getWorkflowId();
    var logger = Workflow.getLogger(this.getClass());

    logger.info("Workflow {} is starting", workflowId);

    orderActivity.saveOrder(order);

    var payment = paymentAcitivity.verifyPaymentMethod(order);

    paymentAcitivity.commitPayment(payment);

    order.setPaid(true);

    orderActivity.updateOrder(order);

  }
}