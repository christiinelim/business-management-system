import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordValidator } from '../../../shared/validators/password.validator';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Subscription } from 'rxjs';
import { CoreModule } from '../../../core/core.module';
import { FeaturesModule } from '../../features.module';
import { Account } from '../../../core/models/account/account.model';
import { Router } from '@angular/router';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FeaturesModule, CoreModule, HeaderComponent, FooterComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  confirmPasswordInvalid: boolean = false;
  subscription: Subscription | undefined;
  submitError: boolean = false;
  errorMessage: string = "";
  loading: boolean = false; 

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
    if (!formData.name || !formData.contact || !formData.instagram || !formData.tiktok ||
        !formData.carousell || !formData.email || !formData.password || !formData.confirmPassword) {
      this.submitError = true;
      this.errorMessage = "Please fill up all the fields";
    } else {
      this.loading = true;
      this.submitError = false;
      const { name, email, password, contact, instagram, tiktok, carousell } = formData;
      const account: Account = { name, email, password, contact, instagram, tiktok, carousell };
      this.authenticationService.signup(account)
        .subscribe((response: any) => {
          this.submitError = false;
          this.loading = false;
          this.router.navigateByUrl('/verification', { state: { email: formData.email }});
        }, (error: any) => {
          if (error.error == "Error: Account does not exists") {
            this.submitError = true;
            this.loading = false;
            this.errorMessage = "Email already exists";
          }
        });
    }
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
