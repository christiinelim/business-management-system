import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HeadersInterceptorService implements HttpInterceptor {

  constructor(private cookieService: CookieService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token = this.cookieService.get('token');

    if (!req.url.includes('/api/auth') && token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      const authReq = req.clone({ headers });

      return next.handle(authReq);
    }
    return next.handle(req);
  }
}
