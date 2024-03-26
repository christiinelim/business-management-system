import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./features/home/components/landing-page/landing-page.component').then(c => c.LandingPageComponent)
    }
];
