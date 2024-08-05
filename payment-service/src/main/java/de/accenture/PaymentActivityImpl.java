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
  public PaymentDto verifyPaymentMethod(OrderDto order) {
    log.info("Payment for order {} is ready", order.getId());
    return PaymentDto.builder()
                     .paymentId(UUID.randomUUID().toString())
                     .orderId(order.getId()).build();
  }

  @Override
  public void commitPayment(PaymentDto payment) {
    log.info("Payment for order {} is committed", payment.getOrderId());
  }
}