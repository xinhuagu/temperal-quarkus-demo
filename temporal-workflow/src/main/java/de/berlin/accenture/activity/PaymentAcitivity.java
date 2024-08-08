package de.berlin.accenture.activity;

import de.berlin.accenture.model.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentAcitivity {

  @ActivityMethod
  OrderDto commitPayment(OrderDto orderDto);

  @ActivityMethod
  OrderDto rollbackPayment(OrderDto payment);

}