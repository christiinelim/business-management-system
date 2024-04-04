import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GlobalService } from '../global/global.service';
import { CookieService } from 'ngx-cookie-service';
import { Item } from '../../models/item/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(
    private http: HttpClient,
    private globalService: GlobalService,
    private cookieService: CookieService
  ) { }

  getItemsByAccountId() {
    const accountId = this.cookieService.get('id');
    return this.http.get(this.globalService.apiUrl + '/item/account/' + accountId);
  }

  createItem(item: Item) {
    return this.http.post<Item>(this.globalService.apiUrl + '/item', item);
  }

  updateItem(itemId: any, item: Item) {
    return this.http.put<Item>(this.globalService.apiUrl + '/item/' + itemId, item);
  }

  deleteItem(itemId: any) {
    return this.http.delete(this.globalService.apiUrl + '/item/' + itemId);
  }
}
