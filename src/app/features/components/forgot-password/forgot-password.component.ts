import { Component, OnInit } from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { passwordValidator } from '../../../shared/validators/password.validator';
import { ResetPassword } from '../../../core/models/reset-password/reset-password.model';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { Router } from '@angular/router';
import { ForgotPassword } from '../../../core/models/forgot-password/forgot-password.model';
import { CoreModule } from '../../../core/core.module';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FeaturesModule, CoreModule, HeaderComponent, FooterComponent],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit {
  reset: boolean = false;
  resetForm!: FormGroup;
  confirmPasswordInvalid: boolean = false;
  description: string = "Enter your email address to reset your password";
  emailed: boolean = false;
  submitError: boolean = false;
  errorMessage: string = "";
  loading: boolean = false; 

  constructor (private authenticationService: AuthenticationService, private router: Router) {

  }

  ngOnInit(): void {

    // regex pattern
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;

    this.resetForm = new FormGroup({
      email: new FormControl("", [Validators.required]),
      resetToken: new FormControl(""),
      newPassword: new FormControl("", [Validators.required, passwordValidator(passwordRegex)]),
      confirmPassword: new FormControl("", [Validators.required])
    })
  }

  checkConfirmPassword() {
    const passwordControl = this.resetForm.get('newPassword')?.value;
    const confirmPasswordControl = this.resetForm.get('confirmPassword')?.value;

    if (passwordControl && confirmPasswordControl) {
      this.confirmPasswordInvalid = passwordControl !== confirmPasswordControl;
    }
  }

  onSubmitReset(formData: any) {
    if (!formData.email && !this.reset) {
      this.submitError = true;
      this.errorMessage = "Please indicate email";
    } else {
      this.submitError = false;
      if (!this.reset) {
        this.loading = true;
        const { email } = formData;
        const forgotPassword: ForgotPassword = { email };
        this.authenticationService.forgot(forgotPassword)
          .subscribe((response: any) => {
            this.reset = true;
            this.description = "";
            this.emailed = true;
            this.loading = false;
          }, (error: any) => {
            if (error.error === "Error: Account does not exist, please sign up"){
              this.loading = false;
              this.submitError = true;
              this.errorMessage = "Account does not exist, please sign up";
            }
          });
      } else if (this.reset) {
        if (!formData.email || !formData.resetToken || !formData.confirmPassword || !formData.newPassword) {
          this.submitError = true;
          this.errorMessage = "Please fill up all the fields";
        } else {
          this.submitError = false;
          // submit form
          const { email, resetToken, newPassword } = formData;
          const resetPassword: ResetPassword = { email, resetToken, newPassword };
          this.authenticationService.reset(resetPassword)
            .subscribe((response: any) => {
              this.router.navigateByUrl('/login', { state: { display: true, message: "Your password has been successfully updated" } });
            }, (error: any) => {
              if (error.error === "Error: Reset token is incorrect"){
                this.submitError = true;
                this.errorMessage = "Invalid reset token";
              } else if (error.error === "Token has expired. Please generate a new one.") {
                this.submitError = true;
                this.errorMessage = "Token has expired, please generate a new one.";
              }
            });
        }
      }
    }
  }

}
