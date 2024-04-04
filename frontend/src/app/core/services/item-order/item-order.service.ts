import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { ItemOrder } from '../../models/item-order/item-order.model';

@Injectable({
  providedIn: 'root'
})
export class ItemOrderService {

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
  ) { }

  getItemOrderByOrderId(orderId: number) {
    return this.http.get(this.globalService.apiUrl + '/purchase/order/' + orderId);
  }

  createPurchase(itemOrder: ItemOrder) {
    return this.http.post<ItemOrder>(this.globalService.apiUrl + '/purchase', itemOrder);
  }

  updatePurchase(itemOrder: ItemOrder, purchaseId: number) {
    return this.http.put<ItemOrder>(this.globalService.apiUrl + '/purchase/' + purchaseId, itemOrder);
  }

  deletePurchase(purchaseId: number) {
    return this.http.delete(this.globalService.apiUrl + '/purchase/' + purchaseId);
  }
}
