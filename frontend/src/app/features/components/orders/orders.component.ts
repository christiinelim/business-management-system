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
import { FormControl, FormGroup, Validators } from '@angular/forms';

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
  protected purchaseData: Purchase[] = [];
  protected totalCost: number = 0;
  protected editOrderId: number = 0;
  protected deleteOrderId: number = 0;
  protected showFormPopup: boolean = false;
  protected error: boolean = false;
  protected showDeletePopup: boolean = false;
  protected showAddItemForm: boolean = true;
  protected orderForm!: FormGroup;
  protected customerOrderToDelete: string = "";
  protected formHeader: string = "";

  constructor(
    private orderService: OrderService, 
    private authenticationService: AuthenticationService, 
    private router: Router,
    private itemOrderService: ItemOrderService) {
  }

  ngOnInit(): void {
    this.refreshData();

    this.orderForm = new FormGroup({
      collectionDate: new FormControl("", [Validators.required]),
      note: new FormControl("", [Validators.required]),
      paid: new FormControl("", [Validators.required]),
      status: new FormControl("", [Validators.required])
    })
  }


  // Retrieve Orders
  refreshData() {
    this.orderService.getOrdersByAccountId()
      .subscribe((response: any) => {
        this.orders = response.data;
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

  // open form
  openForm(order: any) {
    this.formHeader = "Update Order";
    this.showFormPopup = true;

    this.editOrderId = order.orderId;
      this.orderForm.setValue({
        collectionDate: order.collectionDate,
        note: order.note,
        paid: order.paid,
        status: order.status
      });
  }

  // submit form
  submitOrderForm(formData: any) {
    if (!formData.collectionDate || !formData.note || !formData.paid || !formData.status) {
      this.error = true;
    } else {
      this.error = false;
      this.showFormPopup = false;

      const { collectionDate, note, paid, status } = formData;
      const order: Order = {
        collectionDate,
        note,
        paid,
        status
      };

      // submit form
      this.orderService.updateOrder(this.editOrderId, order)
          .subscribe((response: any) => {
            this.showFormPopup = false;
            this.refreshData();
          }, (error: any) => {
            console.log(error)
          }); 
    }
  }

  // delete popup
  deletePopUp(customerName: any, orderId: any) {
    this.customerOrderToDelete = customerName;
    this.showDeletePopup = true;
    this.deleteOrderId = orderId;
  }

  // delete 
  deleteOrder() {
    this.orderService.deleteOrder(this.deleteOrderId)
      .subscribe((response: any) => {
        this.showDeletePopup = false;
        this.refreshData();
      }, (error: any) => {
        console.log(error)
      });
  }
  
}
