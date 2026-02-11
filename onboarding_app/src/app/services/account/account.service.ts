import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environment/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private readonly BASE_URL = environment.apiUrl + '/accounts';

  constructor(
    private httpClient: HttpClient
  ) { }

  getCustomerAccounts(customerId: number) : Observable<any> {

    const params = new HttpParams().set('customerId', customerId.toString());

    return this.httpClient.get(this.BASE_URL, { params });
  }

  createAccount(customerId: number): Observable<any> {

    const accountData = {
      customerId: customerId
    };
    return this.httpClient.post(this.BASE_URL, accountData);
  }

}
