import { NgModule } from '@angular/core';
import { AuthenticationService } from './services/authentication/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { GlobalService } from './services/global/global.service';
import { VerificationService } from './services/verification/verification.service';
import { LoginService } from './services/login/login.service';



@NgModule({
  declarations: [],
  imports: [
    HttpClientModule
  ],
  providers: [
    AuthenticationService,
    GlobalService,
    VerificationService,
    LoginService
  ]
})
export class CoreModule { }
