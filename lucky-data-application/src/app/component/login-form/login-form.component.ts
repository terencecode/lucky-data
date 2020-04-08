import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.sass']
})
export class LoginFormComponent implements OnInit {

  form:FormGroup;

  constructor(private fb:FormBuilder,
              private authService: AuthService,
              private router: Router) {

    this.form = this.fb.group({
      email: ['',Validators.required],
      password: ['',Validators.required]
    });
  }

  login() {
    const values = this.form.value;

    if (values.email && values.password) {
      this.authService.login(values.email, values.password)
        .subscribe(
          () => {
            console.log("User is logged in");
            this.router.navigateByUrl('/datasets');
          }
        );
    }
  }

  ngOnInit(): void {
  }

}
