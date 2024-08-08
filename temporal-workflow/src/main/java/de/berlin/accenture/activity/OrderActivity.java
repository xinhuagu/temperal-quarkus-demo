package de.berlin.accenture.activity;

import de.berlin.accenture.model.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivity {

  @ActivityMethod
  OrderDto createOrder(OrderDto order);

  @ActivityMethod
  OrderDto startShipping(OrderDto order);

  @ActivityMethod
  OrderDto cancelOrder(OrderDto order);

}