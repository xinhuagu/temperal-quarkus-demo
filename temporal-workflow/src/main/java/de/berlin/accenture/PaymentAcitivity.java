package de.berlin.accenture;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentAcitivity {

  @ActivityMethod
  PaymentDto verifyPaymentMethod(OrderDto order);

  @ActivityMethod
  void commitPayment(PaymentDto payment);

  @ActivityMethod
  void rollbackPayment(PaymentDto payment);

}