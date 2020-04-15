import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.sass']
})
export class NavbarComponent implements OnInit {

  constructor(private authService: AuthService,  private router: Router) { }

  public isLoggedIn() {
    return this.authService.isLoggedIn();
  }

  logout(){
    console.log('User is logged out');
    this.authService.logout();
    this.router.navigateByUrl('/');
  }

  ngOnInit(): void {
  }

}
