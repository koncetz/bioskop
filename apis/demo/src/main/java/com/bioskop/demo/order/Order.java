package com.bioskop.demo.order;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.Table;

// import com.bioskop.demo.customer.Customer;
// import com.bioskop.demo.ticket.Ticket;

@Entity
@Table(name = "tbl_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(name = "id_ticket")
  public Long id_ticket;

  @Column(name = "id_customer")
  public Long id_customer;

  @Column(name = "quantity")
  public Integer quantity;
}