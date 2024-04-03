import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { ItemService } from '../../../core/services/item/item.service';
import { FeaturesModule } from '../../features.module';
import { Item } from '../../../core/models/item/item.model';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-listings',
  standalone: true,
  imports: [NavComponent, FeaturesModule],
  templateUrl: './listings.component.html',
  styleUrl: './listings.component.css'
})

export class ListingsComponent implements OnInit{
  protected items: Item[] = [];
  protected showFormPopup: boolean = false;
  protected error: boolean = false;
  protected showDeletePopup: boolean = false;
  protected errorMessage: string = "Missing input fields";
  protected productToDelete: string = "";
  protected productForm!: FormGroup;
  protected deleteItemId: number = 0;
  protected editItemId: number = 0;
  protected status: string = "";
  protected action: string = "";
  protected formHeader: string = "";

  constructor(private itemService: ItemService, private authenticationService: AuthenticationService, private router: Router, private cookieService: CookieService) {

  }

  ngOnInit(): void {
    this.refreshData();

    this.productForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      cost: new FormControl("", [Validators.required]),
      description: new FormControl("", [Validators.required]),
      reference: new FormControl("", [Validators.required]),
      stock_on_hand: new FormControl("", [Validators.required])
    })
  }

  submitForm(formData: any) {
    if (!formData.name || !formData.cost || !formData.description || !formData.reference || !formData.stock_on_hand) {
      this.error = true;
    } else {
      this.error = false;
      const accountId = this.cookieService.get('id');
      const { name, cost, description, reference, stock_on_hand } = formData;
      const item: Item = {
        name,
        cost,
        description,
        reference,
        stock_on_hand,
        account: {
          accountId: parseInt(accountId)
        }
      };

      if (this.status === "add"){
        this.itemService.createItem(item)
          .subscribe((response: any) => {
            this.showFormPopup = false;
            this.refreshData();
          }, (error: any) => {
            console.log(error)
          }); 
      } else {
        this.itemService.updateItem(this.editItemId, item)
          .subscribe((response: any) => {
            this.showFormPopup = false;
            this.refreshData();
          }, (error: any) => {
            console.log(error)
          }); 
      }
    }
  }


  // Retrieve Items listed
  refreshData() {
    this.itemService.getItemsByAccountId(this.cookieService.get('id'))
      .subscribe((response: any) => {
        this.items = response.data;
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }

  // show form 
  openForm (status: any, action: any, formHeader: any, item: any = null) {
    if (status === "edit" && item) {
      this.editItemId = item.itemId;
      this.productForm.setValue({
        name: item.name,
        description: item.description,
        cost: item.cost,
        stock_on_hand: item.stock_on_hand,
        reference: item.reference
      });
    }
    this.status = status;
    this.action = action;
    this.formHeader = formHeader;
    this.showFormPopup = true;
  } 

  // delete popup
  deletePopup(itemName: any, itemId: any) {
    this.showDeletePopup = true;
    this.productToDelete = itemName;
    this.deleteItemId = itemId;
  }

  // delete item
  deleteItem() {
    this.itemService.deleteItem(this.deleteItemId)
      .subscribe((response: any) => {
        this.showDeletePopup = false;
        this.refreshData();
      }, (error: any) => {
        console.log(error)
      });
  }

}
