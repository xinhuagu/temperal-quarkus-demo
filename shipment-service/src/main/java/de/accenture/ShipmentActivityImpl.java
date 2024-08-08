package de.accenture;

import de.berlin.accenture.ShipmentActivity;
import de.berlin.accenture.ShipmentDto;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ShipmentActivityImpl implements ShipmentActivity {

  @Override
  public void startShipment(ShipmentDto shipmentDto) {
    log.info("Order Id {} is shipped", shipmentDto.getOrderId());
//    throw new RuntimeException();
  }
}