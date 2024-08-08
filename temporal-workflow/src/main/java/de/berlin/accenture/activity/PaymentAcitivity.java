package de.berlin.accenture.activity;

import de.berlin.accenture.model.PaymentDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentAcitivity {

  @ActivityMethod
  void commitPayment(PaymentDto payment);

  @ActivityMethod
  void rollbackPayment(PaymentDto payment);

}