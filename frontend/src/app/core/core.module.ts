import { NgModule } from '@angular/core';
import { AuthenticationService } from './services/authentication/authentication.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { GlobalService } from './services/global/global.service';
import { ItemService } from './services/item/item.service';
import { HeadersInterceptorService } from './services/headers-interceptor/headers-interceptor.service';
import { OrderService } from './services/order/order.service';



@NgModule({
  declarations: [],
  imports: [
    HttpClientModule
  ],
  providers: [
    AuthenticationService,
    GlobalService,
    ItemService,
    OrderService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HeadersInterceptorService,
      multi: true
    }
  ]
})
export class CoreModule { }
