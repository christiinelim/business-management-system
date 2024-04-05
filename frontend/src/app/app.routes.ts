import { Routes } from '@angular/router';
import { HomeComponent } from './features/components/home/home.component';
import { SignupComponent } from './features/components/signup/signup.component';
import { LoginComponent } from './features/components/login/login.component';
import { VerificationComponent } from './features/components/verification/verification.component';
import { ForgotPasswordComponent } from './features/components/forgot-password/forgot-password.component';
import { authenticationGuard } from './core/guards/authentication/authentication.guard';
import { ListingsComponent } from './features/components/listings/listings.component';
import { OrdersComponent } from './features/components/orders/orders.component';
import { ProfileComponent } from './features/components/profile/profile.component';
import { CompletedOrdersComponent } from './features/components/completed-orders/completed-orders.component';

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
    },
    {
        path: 'listings', 
        component: ListingsComponent,
        canActivate: [authenticationGuard]
    },
    {
        path: 'orders', 
        component: OrdersComponent,
        canActivate: [authenticationGuard]
    },
    {
        path: 'completed', 
        component: CompletedOrdersComponent,
        canActivate: [authenticationGuard]
    },
    {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [authenticationGuard]
    }
];
