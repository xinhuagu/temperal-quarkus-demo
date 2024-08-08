package de.berlin.accenture.activity;

import de.berlin.accenture.model.OrderDto;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ShipmentActivity {

  @ActivityMethod
  OrderDto startShipment(OrderDto orderDto);

}