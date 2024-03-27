import { Routes } from '@angular/router';
import { HomeComponent } from './features/components/home/home.component';
import { SignupComponent } from './features/components/signup/signup.component';

export const routes: Routes = [
    {
        path: '',
        children: [{ path: '', component: HomeComponent}]
    },
    {
        path: 'login',
        children: [{ path: '', component: SignupComponent}]
    }
];
