package de.accenture;


import de.berlin.accenture.activity.OrderActivity;
import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderDto.Status;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OrderActivityImpl implements OrderActivity
{

  @Override
  public OrderDto createOrder(OrderDto orderDto) {
    orderDto.setStatus(Status.CREATED);
    log.info("Step 1 => Order Id: {} , Status: {}", orderDto.getId(), orderDto.getStatus());
    return orderDto;
  }

  @Override
  public OrderDto startShipping(OrderDto orderDto) {
    orderDto.setStatus(Status.SHIPPING);
    log.info("Step 3 => Order Id: {} , Status: {}", orderDto.getId(), orderDto.getStatus());
    return orderDto;
  }

  @Override
  public OrderDto cancelOrder(OrderDto orderDto) {
    orderDto.setStatus(Status.CANCELED);
    log.info("Compensation [CANCELLATION] => Order Id: {} , Status -> {}", orderDto.getId(), orderDto.getStatus());
    return orderDto;
  }

}