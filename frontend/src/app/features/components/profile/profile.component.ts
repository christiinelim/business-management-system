import { Component, OnInit } from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { AccountService } from '../../../core/services/account/account.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Account } from '../../../core/models/account/account.model';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FeaturesModule, CoreModule, NavComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})

export class ProfileComponent implements OnInit {
  protected account: Account | undefined;

  constructor(private accountService: AccountService, private router: Router, private authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.getAccountData();
  }

  getAccountData() {
    this.accountService.getAccount()
      .subscribe((response: any) => {
        this.account = response.data;
      }, (error: any) => {
        if (error.error.Error === "The JWT signature is invalid or the token has expired"){
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('/login');
        }
      });
  }
}
