import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountHolderInterface } from '../component/account-holder-interface';
import { TransactionLogInterface } from '../component/transaction-log-interface';

@Injectable({ providedIn: 'root' })
export class Accountholderservice {
  private uri = '/api/v1/accounts/';
  private http = inject(HttpClient);

  getUserById(id: number): Observable<AccountHolderInterface> {
    return this.http.get<AccountHolderInterface>(`${this.uri}${id}`);
  }

  getTransactionsById(id: number): Observable<TransactionLogInterface[]> {
    return this.http.get<TransactionLogInterface[]>(`${this.uri}${id}/transactions`);
  }
}
