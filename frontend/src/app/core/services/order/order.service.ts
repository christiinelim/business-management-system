import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { CookieService } from 'ngx-cookie-service';
import { Order } from '../../models/order/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private cookieService: CookieService
  ) { }

  getOrdersByAccountId() {
    const accountId = this.cookieService.get('id');
    return this.http.get(this.globalService.apiUrl + '/order/account/' + accountId);
  }

  updateOrder(orderId: any, order: Order) {
    return this.http.put<Order>(this.globalService.apiUrl + '/order/' + orderId, order);
  }

  deleteOrder(orderId: any) {
    return this.http.delete(this.globalService.apiUrl + '/order/' + orderId);
  }
}
