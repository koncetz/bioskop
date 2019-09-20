import { Component, OnInit, ViewChild, Renderer2 } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MatSidenav } from '@angular/material/sidenav';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  customers: any;
  customerControls: FormControl;
  expired: boolean;
  filteredCustomers: Observable<any[]>;
  tickets: any;
  forms: FormGroup;

  @ViewChild('order', { static: true }) order: MatSidenav;

  constructor(
    private api: AppService,
    private date: DatePipe,
    private form: FormBuilder,
  ) {
    this.customers = [];
    this.customerControls = new FormControl();
    this.expired = false;
    this.tickets = [];

    this.forms = this.form.group({
      id_customer: [''],
      id_ticket: [''],
      name: [''],
      phone: [''],
      email: [''],
      film: [''],
      time: [''],
      quantity: [0],
    });

    this.filteredCustomers = this.customerControls.valueChanges.pipe(
      startWith(''),
      map((value) => typeof value === 'string' ? value : value['name']),
      map((customer) => customer ? this._filterCustomers(customer) : this.customers.slice()),
    );
  }

  ngOnInit() {
    this.api.getCustomers().subscribe((response) => {
      this.customers = response;
    });

    this.api.getTickets().subscribe((response) => {
      this.tickets = response;
    });
  }

  orderClicked(event: any, ticket: Object) {
    this.order.open();
    this.expired = false;

    if (new Date(ticket['date']) < new Date()) this.expired = true;

    this.forms.get('id_ticket').setValue(ticket['id']);
    this.forms.get('quantity').setValue(0);
    this.forms.get('film').setValue(ticket['film']);
    this.forms.get('time').setValue(this.date.transform(ticket['time_started'], 'HH:mm') + ' - ' + this.date.transform(ticket['time_ended'], 'HH:mm'));
  }

  customerSelected(customer: Object) {
    this.forms.get('id_customer').setValue(customer['id']);
    this.forms.get('name').setValue(customer['name']);
    this.forms.get('phone').setValue(customer['phone']);
    this.forms.get('email').setValue(customer['email']);
  }

  customerName(customer: string) {
    return customer ? customer['name'] : '';
  }

  submit() {
    this.api.postOrder(this.forms.value).subscribe(() => {
      this.forms.reset();
      this.order.close();
    });
  }

  private _filterCustomers(value: string) {
    const filterValue = value.toLowerCase();

    return this.customers.filter((customer) => customer['name'].toLowerCase().indexOf(filterValue) === 0);
  }
}