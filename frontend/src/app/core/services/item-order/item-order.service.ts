import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ItemOrderService {

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private cookieService: CookieService
  ) { }

  getItemOrderByOrderId(orderId: number) {
    return this.http.get(this.globalService.apiUrl + '/purchase/order/' + orderId);
  }
}
