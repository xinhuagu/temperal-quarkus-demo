package de.accenture;


import de.berlin.accenture.activity.PaymentAcitivity;
import de.berlin.accenture.model.PaymentDto;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApplicationScoped
public class PaymentActivityImpl implements PaymentAcitivity {


  @Override
  public void commitPayment(PaymentDto payment) {
    log.info("Payment for order {} is committed", payment.getOrderId());
  }

  @Override
  public void rollbackPayment(PaymentDto payment) {
    log.info("Payment for order {} is rollback", payment.getOrderId());
  }
}