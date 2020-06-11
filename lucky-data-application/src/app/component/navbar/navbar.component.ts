import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import {UserService} from '../../service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass']
})

export class NavbarComponent implements OnInit {

  name = '';

  constructor(private authService: AuthService,  private router: Router, private userService: UserService) { }

  public isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout(){
    this.authService.logout();
    this.router.navigateByUrl('/');
  }

  isAdmin() {
    return this.authService.isAdmin();
  }

  isDataExpert() {
    return this.authService.isDataExpert();
  }

  setName() {
    if(this.isLoggedIn() && localStorage.getItem('lastName') !== null && localStorage.getItem('firstName') !== null) {
      this.name = localStorage.getItem('lastName').toString().charAt(0) + '. ' + localStorage.getItem('firstName').toString();
      return true;
    }
    return false;
  }

  ngOnInit(): void {}

}
