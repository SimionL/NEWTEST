import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Account } from './account';

@Injectable({
  providedIn: 'root',
})
export class AllService {

    constructor(private http: HttpClient) {}

    private static readonly url = 'http://localhost:8080/';

  saveAccount(account: Account): Observable<Boolean> {
    return this.http.post<Boolean>(AllService.url, account, this.generateOptions());
  }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(AllService.url);
  }

  private generateOptions(): {} {
     return {headers: new HttpHeaders({'Content-Type':  'application/json'})};
  }

}