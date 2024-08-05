package de.berlin.accenture;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivity {

  @ActivityMethod
  void saveOrder(OrderDto order);

  @ActivityMethod
  void updateOrder(OrderDto order);

  @ActivityMethod
  void cancelOrder(OrderDto order);

}