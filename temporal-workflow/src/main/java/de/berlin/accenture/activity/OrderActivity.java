package de.berlin.accenture.activity;

import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.PaymentDto;
import de.berlin.accenture.model.ShipmentDto;
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