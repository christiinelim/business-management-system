import { Component} from '@angular/core';
import { FeaturesModule } from '../../features.module';
import { CoreModule } from '../../../core/core.module';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Verification } from '../../../core/models/verification/verification.model';
import { Subscription } from 'rxjs';
import { AuthenticationService } from '../../../core/services/authentication/authentication.service';
import { HeaderComponent } from '../../../shared/components/header/header.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';

@Component({
  selector: 'app-verification',
  standalone: true,
  imports: [FeaturesModule, CoreModule, HeaderComponent, FooterComponent],
  templateUrl: './verification.component.html',
  styleUrl: './verification.component.css'
})

export class VerificationComponent {
  protected verificationForm!: FormGroup;
  protected email: string;
  protected subscription: Subscription | undefined;
  protected verificationError: boolean = false;

  constructor (private route: ActivatedRoute, private authenticationService: AuthenticationService, private router: Router) {
    this.email = "";
  }

  ngOnInit () {
    const state = window.history.state;
    if (state && state.email) {
      this.email = state.email;
    }

    this.verificationForm = new FormGroup({
      digitOne: new FormControl("", [Validators.required]),
      digitTwo: new FormControl("", [Validators.required]),
      digitThree: new FormControl("", [Validators.required]),
      digitFour: new FormControl("", [Validators.required]),
      digitFive: new FormControl("", [Validators.required]),
      digitSix: new FormControl("", [Validators.required])
    })


  }

  
  onDigitInput(e: any, p: any, c: any, n: any) {
    const length = c.value.length;
    const maxLength = c.getAttribute("maxLength");
    if (length == maxLength) {
      if (n != ""){
        n.focus();
      }
    }
    if (e.key === "Backspace") {
      if (p != ""){
        p.focus();
      }
    }
  }

  onSubmitVerification(formData: any) {
    const { digitOne, digitTwo, digitThree, digitFour, digitFive, digitSix } = formData;
    const verificationToken = parseInt(digitOne.toString() + digitTwo.toString() + digitThree.toString() +
                              digitFour.toString() + digitFive.toString() + digitSix.toString());
    const verification: Verification = { email: this.email, verificationToken };
    this.authenticationService.verify(verification)
      .subscribe((response: any) => {
        this.verificationError = false;
        this.router.navigateByUrl('/login', { state: { display: true, message: "Your account has been verified" } });
      }, (error: any) => {
        this.verificationError = true;
      });
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
