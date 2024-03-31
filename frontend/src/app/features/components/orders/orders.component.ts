import { Component } from '@angular/core';
import { NavComponent } from '../../../shared/components/nav/nav.component';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [NavComponent],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent {

}
