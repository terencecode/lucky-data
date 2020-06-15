import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/user';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  public getUsers(): Observable<User[]> {
    return this.http.get<User[]>(environment.baseUrl + '/user/users');
  }

  public deleteUser(email: string){
    return this.http.delete(environment.baseUrl + '/user/delete/' + email);
  }

  public editUserRole(addRole: boolean, role: string, email: string) {
    return this.http.post(environment.baseUrl + '/user/editRole', {addRole, role, email});
  }

  public deleteDataset(datasetId: bigint){
    return this.http.delete(environment.baseUrl + '/dataset/delete/' + datasetId);
  }

}
