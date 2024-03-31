import { Component, OnInit } from '@angular/core';
import { SharedModule } from '../../shared.module';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { CoreModule } from '../../../core/core.module';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [SharedModule, CoreModule],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit {
  isNavExpanded: boolean = false;
  isProfileExpanded: boolean = false;
  currentPage: string = "";
  
  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    const state = window.history.state;
    if (state && state.currentPage) {
      this.currentPage = state.currentPage;
    }
  }

  toggleNav() {
    this.isNavExpanded = !this.isNavExpanded;
  }

  toggleProfile() {
    this.isProfileExpanded = !this.isProfileExpanded
  }

  navigateToHome() {
    this.authenticationService.removeToken();
    this.authenticationService.removeAccountId();
    this.router.navigateByUrl('/login');
  }

  navigateToDashboard() {
    this.router.navigateByUrl('/dashboard', { state: { currentPage: "Dashboard"} });
  }

  navigateToListings() {
    this.router.navigateByUrl('/listings', { state: { currentPage: "Listings"} });
  }

  navigateToOrders() {
    this.router.navigateByUrl('/orders', { state: { currentPage: "Orders"} });
  }
}
