import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CommonModule } from '@angular/common';
import { Account } from '../../model/account.model';
import { AccountService } from '../../services/account/account.service';
import { NotificationService } from '../../services/notification/notification.service';

@Component({
  selector: 'app-customer-accounts',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer-accounts.component.html',
  styleUrl: './customer-accounts.component.scss'
})
export class CustomerAccountsComponent implements OnInit {

  @Input() client: Customer | null = null;
  @Output() onSubmit = new EventEmitter<any>();

  accounts : Account[] = [];

  constructor(
    private accountService : AccountService,
    private notify: NotificationService
  ){}


  ngOnInit(): void {
    this.loadAccounts();
  }


  cancelForm(){
    this.onSubmit.emit(null);
  }

  /**
   * Requests the creation of a new account for the current client.
   */
  requestAccount(){
    if (this.client?.id !== undefined) {
      this.accountService.createAccount(this.client.id).subscribe({
        next: (response) => {
          this.accounts.push(response);
          this.notify.success('Account created successfully!');

        },
        error: (error) => {
          console.error('Error creating account:', error);
        }
      });
    }
  }

  /**
   * Loads the accounts for the current client using the AccountService.
   */
  loadAccounts(){
    if (this.client?.id !== undefined) {
      this.accountService.getCustomerAccounts(this.client.id).subscribe({
        next: (response) => {
          this.accounts = response;
        },
        error: (error) => {
          console.error('Error loading accounts:', error);
        }
      });
    }
  }

}
