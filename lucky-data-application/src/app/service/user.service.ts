import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getUserInfo(): Observable<User> {
    return this.http.get<User>(environment.baseUrl + '/user');
  }

  public updateUser(firstName: string, lastName: string, email: string, password: string) {
    return this.http.put(environment.baseUrl + '/user', {
      firstName,
      lastName,
      email,
      password
    });
  }
}
