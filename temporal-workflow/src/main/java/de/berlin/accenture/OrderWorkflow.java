package de.berlin.accenture;

import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderingResultDto;
import io.temporal.workflow.UpdateMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {
   final static String ORDER_TASK_QUEUE = "orderTaskQueue";
   final static String PAYMENT_TASK_QUEUE = "paymentTaskQueue";
   final static  String SHIPMENT_TASK_QUEUE = "shipmentTaskQueue";

  @WorkflowMethod(name = "Order_Creation")
  void startOrdering(OrderDto order);


  @UpdateMethod
  OrderingResultDto ordering();

}