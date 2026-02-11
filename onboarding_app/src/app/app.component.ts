import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Customer } from './model/customer.model';
import { CustomerService } from './services/customer/customer.service';
import { CustomerFormComponent } from "./components/customer-form/customer-form.component";
import { CustomerAccountsComponent } from "./components/customer-accounts/customer-accounts.component";
import { NotificationComponent } from "./components/notification/notification/notification.component";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { NotificationService } from './services/notification/notification.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, CustomerFormComponent, CustomerAccountsComponent, NotificationComponent, NotFoundComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  customers : Customer[] = [];
  isAddingCustomer: boolean = false;
  isModifyingAccounts: boolean = false;
  customerSelected: Customer | null = null;

  constructor(
    private customerService: CustomerService,
    private notify: NotificationService
  ){}

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers() {
    this.customerService.getCustomers().subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => {
      }
    );
  }

  /**
   * Enables the customer addition mode, which displays the customer form for adding a new customer.
   */
  onAddCustomer(){
    this.isAddingCustomer = true;
  }

  /**
   * Creates a new customer and refreshes the customer list.
   * @param event
   */
  sendCustomerData(event:any){
    if(event == null) {
      this.isAddingCustomer = false;
    }else {

      this.customerService.addCustomer(event).subscribe(
        (response) => {
          this.loadCustomers();
          this.isAddingCustomer = false;
        },
        (error) => {
          // this.notify.error('Server not responding');
        }
      );
    }
  }

  /**
   * Resets the selected customer and exits account management mode.
   */
  submitCustomerAccounts(){
    this.customerSelected = null;
    this.isModifyingAccounts = false;
  }

  /**
   * Sets the selected customer and enables account management mode.
   * @param customerData
   */
  onEditCustomer( customerData: Customer) {
    this.isModifyingAccounts = true;
    this.customerSelected = customerData;
  }
}
