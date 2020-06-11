import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from '@angular/common/http';
import {shareReplay, tap} from 'rxjs/operators';
import * as moment from 'moment';
import {environment} from '../../environments/environment';
import {UserService} from './user.service';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  cachedRequests: Array<HttpRequest<any>> = [];

  constructor(private http: HttpClient, private userService: UserService) { }

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
    localStorage.removeItem('roles');
    localStorage.removeItem('firstName');
    localStorage.removeItem('lastName');
  }

  public isLoggedIn() {
    return moment().isBefore(this.getExpiration());
  }

  isAdmin() {
    if (this.isLoggedIn() && localStorage.getItem('roles') !== null) {
      return localStorage.getItem('roles').indexOf('ROLE_ADMIN') !== -1;
    }
    return false;
  }

  isDataExpert() {
    if (this.isLoggedIn() && localStorage.getItem('roles') !== null) {
      return localStorage.getItem('roles').indexOf('ROLE_DATA_EXPERT') !== -1;
    }
    return false;
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

  resetPasswordEmail(email: string) {
    return this.http.get(environment.baseUrl + '/auth/resetPasswordEmail/' + email);
  }

  private setUserData(){
    this.userService.getUserInfo().subscribe(user => {
      localStorage.setItem('roles', user.role.toString());
      localStorage.setItem('firstName', user.firstName);
      localStorage.setItem('lastName', user.lastName);
    });
  }

  private setSession(authResult) {
    const expiresAt = moment().add(authResult.expiresAt, 'ms');

    localStorage.setItem('accessToken', authResult.accessToken);
    localStorage.setItem('expiresAt', JSON.stringify(expiresAt.valueOf()));
    this.setUserData();
  }
}
