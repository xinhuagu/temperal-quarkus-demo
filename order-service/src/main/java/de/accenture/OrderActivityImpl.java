package de.accenture;


import de.berlin.accenture.activity.OrderActivity;
import de.berlin.accenture.model.OrderDto;
import de.berlin.accenture.model.OrderDto.Status;
import de.berlin.accenture.model.PaymentDto;
import de.berlin.accenture.model.ShipmentDto;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class OrderActivityImpl implements OrderActivity
{

  @Override
  public PaymentDto createOrder(OrderDto orderDto) {
    orderDto.setStatus(Status.CREATED);
    log.info("Order Id: {} , Status ->  {}", orderDto.getId(), orderDto.getStatus());

    var payment = PaymentDto.builder().orderId(orderDto.getId()).paymentId(UUID.randomUUID().toString()).build();
    return payment;
  }

  @Override
  public ShipmentDto updateOrderAndShip(OrderDto orderDto) {
    orderDto.setStatus(Status.SHIPPING);
    log.info("Order Id: {} , Status -> {}", orderDto.getId(), orderDto.getStatus());

    var shipment = ShipmentDto.builder().orderId(orderDto.getId()).shipmentId(UUID.randomUUID().toString()).build();
    return shipment;
  }

  @Override
  public OrderDto cancelOrder(OrderDto orderDto) {
    orderDto.setStatus(Status.CANCELED);
    log.info("Order Id: {} , Status -> {}", orderDto.getId(), orderDto.getStatus());

    return orderDto;
  }

}