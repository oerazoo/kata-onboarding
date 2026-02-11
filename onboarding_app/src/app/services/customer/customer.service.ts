import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';
import { Customer } from '../../model/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly BASE_URL = environment.apiUrl + '/customers';

  constructor(
    private httpClient: HttpClient
  ) { }

  getCustomers(): Observable<any> {
    return this.httpClient.get(this.BASE_URL);
  }

  addCustomer(customer: Customer): Observable<any> {
    return this.httpClient.post(this.BASE_URL, customer);
  }


}
