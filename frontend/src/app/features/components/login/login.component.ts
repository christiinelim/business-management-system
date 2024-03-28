// import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FeaturesModule } from '../../features.module';

@Component({
  selector: 'app-login',
  standalone: true,
  // imports: [ReactiveFormsModule, CommonModule],
  imports: [FeaturesModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {
  protected loginForm!: FormGroup;

  constructor(){
    
  }

  ngOnInit(): void {

    this.loginForm = new FormGroup({
      email: new FormControl("", [Validators.required]),
      password: new FormControl("", [Validators.required])
    })
  }

  submitLoginForm() {
    console.log("Form [Submit] - ", this.loginForm.value);
  }
}
