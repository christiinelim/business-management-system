import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { Login } from '../../models/login/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private globalService: GlobalService) {
  }

  public save(login: Login) {
    return this.http.post<Login>(this.globalService.apiUrl+"/auth/login", login)
  }
}
