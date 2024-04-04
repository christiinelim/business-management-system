import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { CookieService } from 'ngx-cookie-service';
import { Account } from '../../models/account/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private cookieService: CookieService
  ) { }

  getAccount() {
    const accountId = this.cookieService.get('id');
    return this.http.get(this.globalService.apiUrl + '/account/' + accountId);
  }

  updateAccount(account: Account) {
    const accountId = this.cookieService.get('id');
    return this.http.put<Account>(this.globalService.apiUrl + '/account/' + accountId, account);
  }

  deleteAccount() {
    const accountId = this.cookieService.get('id');
    return this.http.delete(this.globalService.apiUrl + '/account/' + accountId);
  }
}
