import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {shareReplay, tap} from 'rxjs/operators';
import * as moment from 'moment';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  cachedRequests: Array<HttpRequest<any>> = [];

  constructor(private http: HttpClient) {

  }

  login(email: string, password: string) {
    return this.http.post(environment.baseUrl + '/auth', {email, password})
      .pipe(tap(res => {
          this.setSession(res);
        }),
        shareReplay());
  }

  register(firstName: string, lastName: string, email: string, password: string , departmentName: string, roleName: string) {
    return this.http.put<{ access_token: string }>(environment.baseUrl + '/auth/user', {
      firstName: firstName,
      lastName: lastName,
      email: email,
      password: password,
      departmentName: departmentName,
      roleName: roleName
    });
  }

  logout() {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('expiresAt');
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem('expiresAt');
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }

  public collectFailedRequest(request): void {
    this.cachedRequests.push(request);
  }

  public retryFailedRequests(): void {
    // retry the requests. this method can
    // be called after the token is refreshed
  }

  private setSession(authResult) {
    const expiresAt = moment().add(authResult.expiresAt, 'ms');

    localStorage.setItem('accessToken', authResult.accessToken);
    localStorage.setItem('expiresAt', JSON.stringify(expiresAt.valueOf()));
  }
}
