import { Component, OnInit } from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { NavComponent } from '../../../shared/components/nav/nav.component';
import { AccountService } from '../../../core/services/account/account.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Account } from '../../../core/models/account/account.model';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FeaturesModule, CoreModule, NavComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})

export class ProfileComponent implements OnInit {
  protected account: Account | undefined;
  protected editMode: boolean = false;
  protected error: boolean = false;
  protected deleteWarning: boolean = false;
  protected deleteAccountMessage: boolean = false;
  protected profileForm!: FormGroup;

  constructor(private accountService: AccountService, private router: Router, private authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
    this.getAccountData();

    this.profileForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      contact: new FormControl("", [Validators.required, Validators.pattern('[0-9]{8}')]),
      instagram: new FormControl("", [Validators.required]),
      tiktok: new FormControl("", [Validators.required]),
      carousell: new FormControl("", [Validators.required])
    })
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

  // edit profile
  onEdit() {
    this.editMode = true;

    this.profileForm.setValue({
      name: this.account?.name,
      contact: this.account?.contact,
      instagram: this.account?.instagram,
      tiktok: this.account?.tiktok,
      carousell: this.account?.carousell
    });

  }

  // exit edit mode
  onExit() {
    this.editMode = false;
    this.error = false;
  }

  // delete account
  deleteAccount() {
    this.deleteAccountMessage = true;
    this.deleteWarning = false;
    
    setTimeout(() => {
      this.accountService.deleteAccount()
        .subscribe((response: any) => {
          this.authenticationService.removeToken();
          this.authenticationService.removeAccountId();
          this.router.navigateByUrl('');
        }, (error: any) => {
          console.log(error)
        }); 
    }, 4000);
  }

  // submit
  submitForm(formData: any) {
    if (!formData.name || !formData.contact || !formData.instagram || !formData.tiktok || !formData.carousell) {
      this.error = true;
    } else {
      this.error = false;
      const { name, contact, instagram, tiktok, carousell } = formData;
      const account: Account = {
        name,
        contact,
        instagram,
        tiktok,
        carousell
      };

      this.accountService.updateAccount(account)
        .subscribe((response: any) => {
          this.editMode = false;
          this.getAccountData();
        }, (error: any) => {
          console.log(error)
        }); 

    }
  }
  
}
