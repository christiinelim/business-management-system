import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordValidator } from '../../../shared/validators/password.validator';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Subscription } from 'rxjs';
import { CoreModule } from '../../../core/core.module';
import { FeaturesModule } from '../../features.module';
import { Account } from '../../../core/models/account/account.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FeaturesModule, CoreModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent implements OnInit {
  protected signupForm!: FormGroup;
  protected confirmPasswordInvalid: boolean = false;
  protected subscription: Subscription | undefined;
  protected emailExists: boolean = false;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
    
  }

  ngOnInit(): void {
    // regex pattern
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;

    this.signupForm = new FormGroup({
      name: new FormControl("", [Validators.required]),
      contact: new FormControl("", [Validators.required, Validators.pattern('[0-9]{8}')]),
      instagram: new FormControl("", [Validators.required]),
      tiktok: new FormControl("", [Validators.required]),
      carousell: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required, Validators.email]),
      password: new FormControl("", [Validators.required, passwordValidator(passwordRegex)]),
      confirmPassword: new FormControl("", [Validators.required])
    })
  }

  onSubmitSignupForm(formData: any) {
    const { name, email, password, contact, instagram, tiktok, carousell } = formData;
    const account: Account = { name, email, password, contact, instagram, tiktok, carousell };
    this.authenticationService.save(account)
      .subscribe((response: any) => {
        this.emailExists = false;
        this.router.navigateByUrl('/verification', { state: { email: formData.email }});
      }, (error: any) => {
        if (error.error == "Error: Account does not exists") {
          this.emailExists = true;
        }
      });
  }

  checkConfirmPassword() {
    const passwordControl = this.signupForm.get('password')?.value;
    const confirmPasswordControl = this.signupForm.get('confirmPassword')?.value;

    if (passwordControl && confirmPasswordControl) {
      this.confirmPasswordInvalid = passwordControl !== confirmPasswordControl;
    }
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
