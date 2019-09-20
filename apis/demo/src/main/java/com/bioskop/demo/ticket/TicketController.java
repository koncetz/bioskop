package com.bioskop.demo.ticket;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bioskop.demo.DemoResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TicketController {
  @Autowired
  TicketRepository ticketRepository;

  @GetMapping("/ticket")
  public List<Ticket> getAllNotes() {
    return ticketRepository.findAll();
  }

  @PostMapping("/ticket")
  public Ticket createNote(@Valid @RequestBody Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  @GetMapping("/ticket/{id}")
  public Optional<Ticket> getNoteById(@PathVariable(value = "id") Long noteId) {
    return ticketRepository.findById(noteId);
  }

  @PutMapping("/ticket/{id}")
  public Ticket updateNote(@PathVariable(value = "id") Long ticketId, @Valid @RequestBody Ticket ticketBody) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new DemoResourceNotFoundException("Ticket", "id", ticketId));

    return ticketRepository.save(ticket);
  }

  @DeleteMapping("/ticket/{id}")
  public ResponseEntity<Ticket> deleteNote(@PathVariable(value = "id") Long ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new DemoResourceNotFoundException("Ticket", "id", ticketId));

    ticketRepository.delete(ticket);

    return ResponseEntity.ok().build();
  }
}