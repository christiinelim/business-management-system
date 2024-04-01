import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { Order } from '../../../core/models/order/order.model';
import { CoreModule } from '../../../core/core.module';
import { FeaturesModule } from '../../features.module';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { OrderService } from '../../../core/services/order/order.service';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [NavComponent, CoreModule, FeaturesModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})

export class OrdersComponent implements OnInit {
  protected orders: Order[] = [];
  protected expandedRowIndex: number = -1;

  constructor(private orderService: OrderService, private authenticationService: AuthenticationService, private router: Router) {
    this.refreshData();
  }

  ngOnInit(): void {
      
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

  // toggle collapse order
  toggleCollapse(index: number) {
    this.expandedRowIndex = this.expandedRowIndex === index ? -1 : index;
}
}
