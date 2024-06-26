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
import { CookieService } from 'ngx-cookie-service';
import { ItemService } from '../../../core/services/item/item.service';
import { ItemOrder } from '../../../core/models/item-order/item-order.model';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [NavComponent, CoreModule, FeaturesModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})

export class OrdersComponent implements OnInit {
  pendingOrders: Order[] = [];
  completedOrders: Order[] = [];
  expandedRowIndex: number = -1;
  purchaseData: Purchase[] = [];
  totalCost: number = 0;
  editOrderId: number = 0;
  deleteOrderId: number = 0;
  deletePurchaseId: number = 0;
  purchaseId: number = 0;
  showFormPopup: boolean = false;
  error: boolean = false;
  showDeletePopup: boolean = false;
  showAddItemForm: boolean = false;
  addItemError: boolean = false;
  showPending: boolean = true;
  isPendingTabActive: boolean = true;
  expandedRowType: string = "Pending"
  orderForm!: FormGroup;
  itemForm!: FormGroup;
  deleteMessage: string = "";
  formHeader: string = "";
  itemsMap: { [key: string]: number } = {};
  status: string = "";
  deleteStatus = "";

  constructor(
    private orderService: OrderService, 
    private authenticationService: AuthenticationService, 
    private router: Router,
    private itemOrderService: ItemOrderService,
    private cookieService: CookieService,
    private itemService: ItemService) {
  }

  ngOnInit(): void {
    this.refreshData();
    this.retrieveItems();


    this.orderForm = new FormGroup({
      collectionDate: new FormControl("", [Validators.required]),
      note: new FormControl("", [Validators.required]),
      paid: new FormControl("", [Validators.required]),
      status: new FormControl("", [Validators.required])
    });

    this.itemForm = new FormGroup({
      itemName: new FormControl("", [Validators.required]),
      quantity: new FormControl("", [Validators.required])
    });
  }


  // retrieve orders
  refreshData() {
    this.orderService.getOrdersByAccountId()
      .subscribe((response: any) => {
        const responseOrders = response.data;
        console.log(responseOrders)
        this.pendingOrders = responseOrders.filter((order: Order) => order.status === 'Pending');
        console.log(this.pendingOrders)
        this.completedOrders = responseOrders.filter((order: Order) => order.status === 'Completed');
        console.log(this.completedOrders)
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }

  // retrive item order
  refreshItemOrderData(orderId: number) {
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

  // calculate total cost of order
  calculateTotalCost() {
    this.totalCost = this.purchaseData.reduce((total, purchase) => {
      return total + purchase.item.cost * purchase.quantity;
    }, 0);
  }

  // toggle between pending and completed
  onPendingTabClick() {
    this.showPending = true; 
    this.expandedRowType = 'Pending'; 
    this.expandedRowIndex = -1;
    this.showAddItemForm = false;
    this.isPendingTabActive = true;
  }

  onCompletedTabClick() {
    this.showPending = false; 
    this.expandedRowType = 'Completed'; 
    this.expandedRowIndex = -1;
    this.showAddItemForm = false;
    this.isPendingTabActive = false;
  }

  // toggle collapse order
  toggleCollapse(index: number, orderId: any) {
    this.showAddItemForm = false;
    this.expandedRowIndex = this.expandedRowIndex === index ? -1 : index;
    this.refreshItemOrderData(orderId);
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
  deletePopUp(messageDetail: any, id: any, status: any) {
    this.showDeletePopup = true;
    if (status === "Delete Order") {
      this.deleteMessage = "Ordered By: " + messageDetail;
      this.deleteOrderId = id;
      this.deleteStatus = "Delete Order";
    } else {
      this.deleteMessage = "Purchase: " + messageDetail;
      this.deletePurchaseId = id;
      this.deleteStatus = "Delete Purchase";
    }
    
  }

  // delete 
  confirmDelete() {
    if (this.deleteStatus === "Delete Order") {
      this.orderService.deleteOrder(this.deleteOrderId)
      .subscribe((response: any) => {
        this.showDeletePopup = false;
        this.refreshData();
      }, (error: any) => {
        console.log(error)
      });
    } else {
      this.itemOrderService.deletePurchase(this.deletePurchaseId)
      .subscribe((response: any) => {
        this.showDeletePopup = false;
        let orderId = -1;
        if (this.showPending) {
          orderId = this.pendingOrders[this.expandedRowIndex].orderId || 0;
        } else {
          orderId = this.completedOrders[this.expandedRowIndex].orderId || 0;
        }
        this.refreshItemOrderData(orderId);
      }, (error: any) => {
        console.log(error)
      });
    }
    
  }

  // add item
  onAddItemClick() {
    this.showAddItemForm = true;
    this.status = "New";
  }

  // edit item
  async onEditItemClick(itemOrder: ItemOrder) {
    this.showAddItemForm = true;
    this.status = "Update";
    this.purchaseId = itemOrder.purchaseId || 0;

    await this.simulateDataLoading();

    this.itemForm.patchValue({
      quantity: itemOrder.quantity,
      itemName: itemOrder.item.name
    });    
  }

  async simulateDataLoading() {
    await new Promise(resolve => setTimeout(resolve, 10)); 
  }

  // retrieve items listed
  retrieveItems() {
    this.itemService.getItemsByAccountId()
      .subscribe((response: any) => {
        const itemsData = response.data;
        itemsData.map((item: any) => this.itemsMap[item.name] = item.itemId);
        console.log(this.itemsMap);
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }

  // get orders depending on show pending
  getOrdersToShow(): Order[] {
    return this.showPending ? this.pendingOrders : this.completedOrders;
  }

  submitItemForm(formData: any, purchaseId?: number) {
    if (!formData.quantity || !formData.itemName) {
      this.addItemError = true;
    } else {
      this.addItemError = false;
      this.showAddItemForm = false;

      const { quantity, itemName } = formData;
      let orderId = -1;
      if (this.showPending) {
        orderId = this.pendingOrders[this.expandedRowIndex].orderId || 0;
      } else {
        orderId = this.completedOrders[this.expandedRowIndex].orderId || 0;
      }
      
      const itemOrder: ItemOrder = {
        quantity, 
        item: {
          itemId: this.itemsMap[itemName]
        },
        order: {
          orderId: orderId
        }
      };

      if (this.status === "New") {
        this.itemOrderService.createPurchase(itemOrder)
            .subscribe((response: any) => {
              this.refreshItemOrderData(orderId);
            }, (error: any) => {
              console.log(error)
            }); 
      } else {
        // update
        this.itemOrderService.updatePurchase(itemOrder, this.purchaseId)
            .subscribe((response: any) => {
              console.log(orderId)
              this.refreshItemOrderData(orderId);
            }, (error: any) => {
              console.log(error)
            }); 
      }
    }
  }
  
}