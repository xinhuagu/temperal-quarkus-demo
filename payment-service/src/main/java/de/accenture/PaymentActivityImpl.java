package de.accenture;


import de.berlin.accenture.activity.PaymentAcitivity;
import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderDto.Status;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ApplicationScoped
public class PaymentActivityImpl implements PaymentAcitivity {


  @Override
  public OrderDto commitPayment(OrderDto orderDto) {
    orderDto.setStatus(Status.PAID);
    log.info("Step 2 => Payment for order {} is committed", orderDto.getId());
    return orderDto;
//    throw new RuntimeException();
  }

  @Override
  public OrderDto rollbackPayment(OrderDto orderDto) {
    orderDto.setStatus(Status.PAYMENT_ROLLBACK);
    log.info("Compensation [PAYMENT ROLLBACK] => Payment for order {} is rollback", orderDto.getId());
    return orderDto;
  }
}