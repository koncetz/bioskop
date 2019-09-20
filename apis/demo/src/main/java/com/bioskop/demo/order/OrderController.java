package com.bioskop.demo.order;

import java.util.List;

import javax.validation.Valid;

import com.bioskop.demo.DemoResourceNotFoundException;
import com.bioskop.demo.ticket.Ticket;
import com.bioskop.demo.ticket.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {
  @Autowired
  OrderRepository orderRepository;

  @Autowired
  TicketRepository ticketRepository;

  @GetMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Order>> findAll() {
    return ResponseEntity.ok(orderRepository.findAll());
  }

  @PostMapping("/order")
  public Order createOrder(@Valid @RequestBody Order order) {
    Ticket ticket = ticketRepository.findById(order.id_ticket)
        .orElseThrow(() -> new DemoResourceNotFoundException("Ticket", "id", order.id_ticket));

    ticket.setQuantity(ticket.getQuantity() - order.quantity);
    ticketRepository.save(ticket);

    return orderRepository.save(order);
  }
}