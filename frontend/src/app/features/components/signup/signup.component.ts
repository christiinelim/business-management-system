import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;

  constructor(private fb: FormBuilder){
    
  }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: ["", [Validators.required]],
      contact: ["", [Validators.required, Validators.pattern('[0-9]{8}')]],
      instagram: ["", [Validators.required]],
      tiktok: ["", [Validators.required]],
      carousell: ["", [Validators.required]],
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required]],
      confirmPassword: ["", [Validators.required]]
    })
  }

  submitSignupForm() {
    console.log("Form [Submit] - ", this.signupForm.value);
  }
}
