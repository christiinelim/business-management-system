import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { passwordValidator } from '../../../shared/validators/password.validator';


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  confirmPasswordInvalid: boolean = false;

  constructor(){
    
  }

  ngOnInit(): void {
    // regex pattern
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[\]:;<>,.?/~_+-=|\\]).{8,32}$/;

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

  submitSignupForm() {
    console.log("Form [Submit] - ", this.signupForm.value);
  }

  checkConfirmPassword() {
    const passwordControl = this.signupForm.get('password')?.value;
    const confirmPasswordControl = this.signupForm.get('confirmPassword')?.value;

    if (passwordControl && confirmPasswordControl) {
      this.confirmPasswordInvalid = passwordControl !== confirmPasswordControl;
    }
  }  
}
