import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.sass']
})
export class RegisterFormComponent implements OnInit {

  departments: any = [];
  form: FormGroup;
  submitted = false;
  hide = true;
  hide2 = true;
  loginError = false;
  errorMess = '';
  passwordRegex = /^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])|(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&\\/=?_.,:;\\\\-])|(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&\\/=?_.,:;\\\\-])|(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&\\/=?_.,:;\\\\-])).{12,30}$/;
  oldpasswordRegex = /^(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}$/;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private http: HttpClient) {

    this.http.get('../../../assets/departmentconfig.json').subscribe((res) => this.departments = res);

    this.form = this.fb.group({
      firstName: ['', Validators.compose([
        Validators.minLength(2),
        Validators.maxLength(30),
        Validators.pattern(/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŠŽ∂ð ,.'-]+$/u),
        Validators.required
      ])],
      lastName: ['', Validators.compose([
        Validators.minLength(2),
        Validators.maxLength(30),
        Validators.pattern(/^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆŠŽ∂ð ,.'-]+$/u),
        Validators.required
      ])],
      department: [null, Validators.required],
      email: ['', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@lcl.fr$')
      ])],
      password: ['', Validators.compose([
        Validators.required,
        Validators.pattern(this.passwordRegex)
      ])],
      passwordConfirm: ['', Validators.required],
      acceptTerms: [false, Validators.requiredTrue]
    }, {
      validator: [
        this.MustMatch('password', 'passwordConfirm')
      ]
    });
  }

  // test if 2 fields are the same
  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];

      if (matchingControl.errors && !matchingControl.errors.mustMatch) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ mustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }

  register() {
    this.submitted = true;
    if (this.form.valid) {
      this.authService.register(this.form.value.firstName, this.form.value.lastName, this.form.value.email,
        this.form.value.password, this.form.value.department, 'user')
        .subscribe(
          () => {
            this.authService.login(this.form.value.email, this.form.value.password)
              .subscribe(
                () => {
                  this.router.navigateByUrl('/datasets');
                },
                (error) => {
                  console.log(error);
                  this.loginError = true;
                  this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
                });
              },
          (error) => {
              console.log(error);
              this.loginError = true;
              if (error.status === 400) {
                this.errorMess = 'Adresse email déjà utilisée';
              }
              else {
                this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
              }
          });
    }
  }

ngOnInit(): void {}

}
