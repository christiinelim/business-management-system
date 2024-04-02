import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { Order } from '../../../core/models/order/order.model';
import { CoreModule } from '../../../core/core.module';
import { FeaturesModule } from '../../features.module';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { OrderService } from '../../../core/services/order/order.service';
import { ItemOrderService } from '../../../core/services/item-order/item-order.service';
import { Purchase } from '../../../core/models/purchase/purchase.model';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [NavComponent, CoreModule, FeaturesModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})

export class OrdersComponent implements OnInit {
  protected orders: Order[] = [];
  protected expandedRowIndex: number = 0; //-1
  protected purchaseData: Purchase[] = [];
  protected totalCost: number = 0;

  constructor(
    private orderService: OrderService, 
    private authenticationService: AuthenticationService, 
    private router: Router,
    private itemOrderService: ItemOrderService) {
  }

  ngOnInit(): void {
    this.refreshData();
  }

  // Retrieve Orders
  refreshData() {
    this.orderService.getOrdersByAccountId()
      .subscribe((response: any) => {
        this.orders = response.data;
        console.log(this.orders)
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }

  // calculate total cost of order
  calculateTotalCost() {
    this.totalCost = this.purchaseData.reduce((total, purchase) => {
      return total + purchase.item.cost * purchase.quantity;
    }, 0);
  }

  // toggle collapse order
  toggleCollapse(index: number, orderId: any) {
    this.expandedRowIndex = this.expandedRowIndex === index ? -1 : index;
    this.itemOrderService.getItemOrderByOrderId(orderId)
      .subscribe((response: any) => {
        this.purchaseData = response.data;
        this.calculateTotalCost();
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }
}
