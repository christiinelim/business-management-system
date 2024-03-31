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
  protected errorMessage: string = "Missing input fields";
  protected productForm!: FormGroup;

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

      this.itemService.createItem(item)
        .subscribe((response: any) => {
          this.showFormPopup = false;
          this.refreshData();
        }, (error: any) => {
          console.log(error)
        }); 
    }
  }

  refreshData() {
    this.itemService.getItemsByAccountId()
      .subscribe((response: any) => {
        this.items = response.data;
        console.log(this.items);
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }
}
