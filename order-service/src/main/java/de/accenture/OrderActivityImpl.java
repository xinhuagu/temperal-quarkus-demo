package de.accenture;

import de.berlin.accenture.OrderActivity;
import de.berlin.accenture.OrderDto;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OrderActivityImpl implements OrderActivity
{

  @Override
  public void saveOrder(OrderDto orderDto) {
    log.info("Order {} is saved", orderDto.getId());
  }

  @Override
  public void updateOrder(OrderDto orderDto) {
    log.info("Order {} is updated", orderDto.getId());
  }

  @Override
  public void cancelOrder(OrderDto orderDto) {
    log.info("Order {} is canceled", orderDto.getId());
  }
}