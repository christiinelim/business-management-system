import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { passwordValidator } from '../../../shared/validators/password.validator';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Seller } from '../../../core/models/seller/seller.model';
import { Subscription } from 'rxjs';
import { CoreModule } from '../../../core/core.module';
import { FeaturesModule } from '../../features.module';


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

  constructor(private authenticationService: AuthenticationService) {
    
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
    console.log(formData)
    const { name, contact, instagram, tiktok, carousell } = formData;
    const seller: Seller = { name, contact, instagram, tiktok, carousell };
    this.authenticationService.save(seller)
      .subscribe((response: any) => {
        console.log(response.data)
      }, (error: any) => {
        console.error('Error:', error);
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
