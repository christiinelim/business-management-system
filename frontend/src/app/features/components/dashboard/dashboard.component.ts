import { Component } from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { NavComponent } from '../../../shared/components/nav/nav.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FeaturesModule, CoreModule, NavComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
