import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Seller } from '../../models/seller/seller.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/auth/signup'
  }

  public save(seller: Seller) {
    return this.http.post<Seller>(this.baseUrl, seller)
  }
}
