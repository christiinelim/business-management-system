import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../../models/account/account.model';
import { GlobalService } from '../global/global.service';
import { Login } from '../../models/login/login.model';
import { Verification } from '../../models/verification/verification.model';
import { CookieService } from 'ngx-cookie-service';
import { tap } from 'rxjs';
import { ForgotPassword } from '../../models/forgot-password/forgot-password.model';
import { ResetPassword } from '../../models/reset-password/reset-password.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    private http: HttpClient, 
    private globalService: GlobalService,
    private cookieService: CookieService
  ) {}

  signup(account: Account) {
    return this.http.post<Account>(this.globalService.apiUrl + '/auth/signup', account);
  }

  verify(verification: Verification) {
    return this.http.post<Verification>(this.globalService.apiUrl + '/auth/verify', verification);
  }

  forgot(forgotPassword: ForgotPassword) {
    return this.http.post<ForgotPassword>(this.globalService.apiUrl + '/auth/forgot-password', forgotPassword);
  }

  reset(resetPassword: ResetPassword) {
    return this.http.post<ResetPassword>(this.globalService.apiUrl + '/auth/reset-password', resetPassword);
  }

  login(login: Login) {
    return this.http.post<any>(this.globalService.apiUrl + '/auth/login', login).pipe(
      tap(response => {
        const token = response.data.token;
        if (token) {
          this.saveToken(token);
        } else {
          console.log("Error signing in")
        }
      })
    );
  }

  private saveToken(token: string) {
    this.cookieService.set('token', token);
  }

  getToken() {
    return this.cookieService.get('token');
  }

  removeToken() {
    this.cookieService.delete('token');
  }
}
