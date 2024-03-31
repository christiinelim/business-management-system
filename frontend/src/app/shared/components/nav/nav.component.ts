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
export class NavComponent{
  isNavExpanded: boolean = false;
  isProfileExpanded: boolean = false;
  
  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  toggleNav() {
    this.isNavExpanded = !this.isNavExpanded;
  }

  toggleProfile() {
    this.isProfileExpanded = !this.isProfileExpanded
  }

  navigateToHome() {
    this.authenticationService.removeToken();
    this.router.navigateByUrl('/login');
  }
}
