package de.accenture;

import de.berlin.accenture.OrderDto;
import de.berlin.accenture.PaymentAcitivity;
import de.berlin.accenture.PaymentDto;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;
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