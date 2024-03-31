import { Component, OnInit } from '@angular/core';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { ItemService } from '../../../core/services/item/item.service';
import { FeaturesModule } from '../../features.module';
import { Item } from '../../../core/models/item/item.model';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listings',
  standalone: true,
  imports: [NavComponent, FeaturesModule],
  templateUrl: './listings.component.html',
  styleUrl: './listings.component.css'
})
export class ListingsComponent implements OnInit{
  protected items: Item[] = [];

  constructor(private itemService: ItemService, private authenticationService: AuthenticationService, private router: Router) {

  }

  ngOnInit(): void {
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
