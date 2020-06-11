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
  emailError = false;
  emailSucces = false;
  errorMess = '';
  successMess = '';

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {

    this.form = this.fb.group({
      email: ['', Validators.compose(
        [Validators.required,
          Validators.email]
          //Validators.pattern('^[a-z0-9._%+-]+@lcl.fr$')]
      )]});
  }

  resetPassword() {
    if (this.form.valid) {
      this.authService.resetPasswordEmail(this.form.value.email).subscribe(
        () => {
          // Mettre en place l'envoi de mail
          this.emailSucces = true;
          this.successMess = 'Un email contenant votre nouveau mot de passe a été envoyé';
        },
        (error) => {
          if (error.status === 409) {
            //Email don't exist, we don't send the email but say succes to user for security
            this.emailSucces = true;
            this.successMess = 'Un email contenant votre nouveau mot de passe a été envoyé';
          }
          else {
            console.log(error);
            this.emailError = true;
            this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
          }
        });
      this.ngOnInit();
    }
  }

  ngOnInit(): void {
  }

}
