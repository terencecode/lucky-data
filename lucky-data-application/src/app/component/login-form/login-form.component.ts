import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass']
})
export class LoginFormComponent implements OnInit {

  form: FormGroup;
  hide = true;
  loginError = false;
  errorMess = '';

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {

    this.form = this.fb.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required]
    });
  }

  login() {
    if (this.form.valid) {
      this.authService.login(this.form.value.email, this.form.value.password)
        .subscribe(
          () => {
            this.router.navigateByUrl('/datasets');
          },
          (error) => {
            console.log(error);
            this.loginError = true;
            if (error.status === 401) {
              this.errorMess = 'Email ou mot de passe incorrect';
            }
            else {
              this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
            }

          }
        );
    }
  }

  ngOnInit(): void {
  }

}
