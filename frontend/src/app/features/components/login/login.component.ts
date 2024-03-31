import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { Login } from '../../../core/models/login/login.model';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FeaturesModule, CoreModule, HeaderComponent, FooterComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
  protected loginForm!: FormGroup;
  protected display: boolean = false;
  protected message: string = "";
  protected loginError: boolean = false;
  protected errorMessage: string = "";

  constructor(private authenticationService: AuthenticationService, private router: Router){
    
  }

  ngOnInit(): void {
    const state = window.history.state;
    if (state) {
      if (state.display) {
        this.display = state.display;
        this.message = state.message;
      }  else if (state.loginError) {
        this.loginError = state.loginError;
        this.errorMessage = state.errorMessage;
      }
    }

    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required])
    })
  }

  navigateToForgotPassword() {
    this.router.navigateByUrl('/forgot');
  }

  submitLoginForm(formData: any) {
    if (!formData.email || !formData.password) {
      this.loginError = true;
      this.errorMessage = "Please fill up all the fields";
    } else {
      this.loginError = false;
      const { email, password } = formData;
      const login: Login = { email, password };
      this.authenticationService.login(login)
        .subscribe((response: any) => {
          this.loginError = false;
          this.router.navigateByUrl('/dashboard');
        }, (error: any) => {
          
          if (error.error === "Error: Account does not exist, please sign up"){
            this.errorMessage = "Account does not exist, please sign up";
          } else if (error.error === "Error: Wrong email or password"){
            this.errorMessage = "Wrong email or password";
          }
          this.loginError = true;
        });
    }
  }
}
