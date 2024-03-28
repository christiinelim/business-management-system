import { Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-verification',
  standalone: true,
  imports: [FeaturesModule, CoreModule],
  templateUrl: './verification.component.html',
  styleUrl: './verification.component.css'
})
export class VerificationComponent implements OnInit{

  protected verificationForm!: FormGroup;

  @ViewChildren('digitInput') digitInputs!: QueryList<HTMLInputElement>;

  constructor () {

  }

  ngAfterViewInit() {
    this.digitInputs.first.focus();
  }

  ngOnInit(): void {
    this.verificationForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email])
    })
  }

  onDigitInput(inputElement: HTMLInputElement, nextIndex: number | null) {
    const value = inputElement.value;
    if (value && value.length === 1 && nextIndex !== null) {
      const nextInput = this.digitInputs.toArray()[nextIndex];
      if (nextInput) {
        nextInput.focus();
      }
    }
  }

  onSubmitVerification(formData: any) {
    
  }
}
