package com.bioskop.demo.ticket;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_ticket")
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @Column(name = "film")
  public String film;

  @Column(name = "date")
  @Temporal(TemporalType.DATE)
  public Date date;

  @Column(name = "time_started")
  @Temporal(TemporalType.TIMESTAMP)
  public Date time_started;

  @Column(name = "time_ended")
  @Temporal(TemporalType.TIMESTAMP)
  public Date time_ended;

  @Column(name = "quantity")
  private Integer quantity;

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}