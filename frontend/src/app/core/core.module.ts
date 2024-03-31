import { NgModule } from '@angular/core';
import { AuthenticationService } from './services/authentication/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { GlobalService } from './services/global/global.service';
import { authenticationGuard } from './guards/authentication/authentication.guard';



@NgModule({
  declarations: [],
  imports: [
    HttpClientModule
  ],
  providers: [
    AuthenticationService,
    GlobalService
  ]
})
export class CoreModule { }
