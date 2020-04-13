import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.sass']
})
export class ForgotPasswordComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {

    this.form = this.fb.group({
      email: ['', Validators.compose(
        [Validators.required,
          Validators.pattern('^[a-z0-9._%+-]+@lcl.fr$')]
      )]});
  }

  resetPassword() {
    if (this.form.valid) {
      // Mettre en place l'envoi de mail
      return this.router.navigateByUrl('');
    }
  }

  ngOnInit(): void {
  }

}
