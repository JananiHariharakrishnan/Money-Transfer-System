import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../service/auth';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpinterceptorService implements HttpInterceptor{

  constructor(private authService : AuthService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getAuthToken();
    if (token) {
      const request = req.clone({ setHeaders: { Authorization: `Basic ${token}` } });
      return next.handle(request);
    }
    return next.handle(req);
  }
}