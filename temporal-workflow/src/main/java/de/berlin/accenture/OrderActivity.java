package de.berlin.accenture;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivity {

  @ActivityMethod
  PaymentDto createOrder(OrderDto order);

  @ActivityMethod
  ShipmentDto updateOrderAndShip(OrderDto order);

  @ActivityMethod
  OrderDto cancelOrder(OrderDto order);

}