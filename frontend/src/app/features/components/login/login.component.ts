import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { Login } from '../../../core/models/login/login.model';
import { LoginService } from '../../../core/services/login/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FeaturesModule, CoreModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
  protected loginForm!: FormGroup;
  protected verified: boolean = false;
  protected loginError: boolean = false;
  protected errorMessage: string = "";

  constructor(private loginService: LoginService, private router: Router){
    
  }

  ngOnInit(): void {
    const state = window.history.state;
    if (state && state.verified) {
      this.verified = state.verified;
    }

    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required])
    })
  }

  submitLoginForm(formData: any) {
    const { email, password } = formData;
    const login: Login = { email, password };
    this.loginService.save(login)
      .subscribe((response: any) => {
        this.loginError = false;
        this.router.navigateByUrl('');
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
