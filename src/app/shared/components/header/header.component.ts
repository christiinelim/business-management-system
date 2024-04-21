import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(private router: Router) {}

  navigateToHome() {
    this.router.navigateByUrl('');
  }

  navigateToSignUp() {
    this.router.navigateByUrl('/signup');
  }

  navigateToLogin() {
    this.router.navigateByUrl('/login');
  }
  
}
