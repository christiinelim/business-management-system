import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../../models/account/account.model';
import { GlobalService } from '../global/global.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private http: HttpClient, private globalService: GlobalService) {
    
  }

  public save(account: Account) {
    return this.http.post<Account>(this.globalService.apiUrl+"/auth/signup", account)
  }
}
