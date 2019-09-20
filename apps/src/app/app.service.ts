import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  constructor(
    private http: HttpClient,
  ) {

  }

  getTickets() {
    return this.http.get('/api/ticket');
  }

  putTicket(data: Object) {
    return this.http.put('/api/ticket', data);
  }

  postTicket(data: Object) {
    return this.http.post('/api/ticket', data);
  }

  deleteTicket(id: String) {
    return this.http.delete('/api/ticket' + id);
  }

  getCustomers() {
    return this.http.get('/api/customer');
  }

  putCustomer(data: Object) {
    return this.http.put('/api/customer', data);
  }

  postCustomer(data: Object) {
    return this.http.post('/api/customer', data);
  }

  deleteCustomer(id: String) {
    return this.http.delete('/api/customer' + id);
  }

  getOrders() {
    return this.http.get('/api/order');
  }

  postOrder(data: Object) {
    return this.http.post('/api/order', data);
  }
}
