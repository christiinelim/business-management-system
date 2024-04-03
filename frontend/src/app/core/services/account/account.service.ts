import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { CookieService } from 'ngx-cookie-service';

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
}
