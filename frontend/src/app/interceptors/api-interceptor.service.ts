import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppConfig} from '../app.config';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.substr(0, 4) === '/api') {
      const copy = req.clone({url: AppConfig.settings.server.api + req.url});
      return next.handle(copy);
    } else {
      return next.handle(req);
    }
  }
}
