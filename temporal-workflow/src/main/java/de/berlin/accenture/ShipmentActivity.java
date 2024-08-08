package de.berlin.accenture;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ShipmentActivity {

  @ActivityMethod
  void startShipment(ShipmentDto shipment);

}