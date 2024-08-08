package de.accenture;


import de.berlin.accenture.activity.ShipmentActivity;
import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderDto.Status;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class ShipmentActivityImpl implements ShipmentActivity {

  @Override
  public OrderDto startShipment(OrderDto orderDto) {
    orderDto.setStatus(Status.SHIPPED);
    log.info("Step 3 => Order Id {}, Status: {}", orderDto.getId(), orderDto.getStatus());
    return orderDto;
//    throw new RuntimeException();
  }
}