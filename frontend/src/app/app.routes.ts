import { Routes } from '@angular/router';
import { HomeComponent } from './features/components/home/home.component';
import { SignupComponent } from './features/components/signup/signup.component';
import { LoginComponent } from './features/components/login/login.component';
import { VerificationComponent } from './features/components/verification/verification.component';
import { ForgotPasswordComponent } from './features/components/forgot-password/forgot-password.component';

export const routes: Routes = [
    {
        path: '',
        children: [{ path: '', component: HomeComponent }]
    },
    {
        path: 'signup',
        children: [{ path: '', component: SignupComponent }]
    },
    {
        path: 'login',
        children: [{ path: '', component: LoginComponent }]
    },
    {
        path: 'verification',
        children: [{ path: '', component: VerificationComponent }]
    },
    {
        path: 'forgot',
        children: [{ path: '', component: ForgotPasswordComponent }]
    }
];
