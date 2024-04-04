import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

export const authenticationGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const cookieService = inject(CookieService);

  const token = cookieService.get('token');

  if (token) {
    return true;
  } else {
    router.navigateByUrl('/login', { state: { loginError: true, errorMessage: "Please login" } });
    return false;
  }

  
};
