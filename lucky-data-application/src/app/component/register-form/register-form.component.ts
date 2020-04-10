import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Router} from '@angular/router';

interface Department {
  name: string;
}

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.sass']
})
export class RegisterFormComponent implements OnInit {

  departments: Department[] = [
    {name: 'Marketing'},
    {name: 'Risque'},
    {name: 'Crédit'},
    {name: 'Partenariats'}
  ];

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {

    this.form = this.fb.group({
      fistName: ['', Validators.compose([
        Validators.minLength(2),
        Validators.maxLength(30),
        Validators.pattern('[a-zA-Z ]*'),
        Validators.required
      ])],
      lastName: ['', Validators.compose([
        Validators.minLength(2),
        Validators.maxLength(30),
        Validators.pattern('[a-zA-Z ]*'),
        Validators.required
      ])],
      department: [null, Validators.required],
      email: ['', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@lcl.fr$')
      ])],
      password: ['', Validators.compose([
        Validators.required,
        Validators.pattern(/^(?=\D*\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}$/)
        // 8 char + une maj + un chiffre + un special
        // Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,30}')
      ])],
      passwordConfirm: ['', Validators.required],
      acceptTerms: [false, Validators.requiredTrue]
    }, {
      validator: [
        this.MustMatch('password', 'passwordConfirm'),
        this.CheckSelect('department')
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

  CheckSelect(controlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];

      if (control.errors && !control.errors.mustMatch) {
        return;
      }
      if (!control.value) {
        control.setErrors({ mustMatch: true });
      } else {
        control.setErrors(null);
      }
    };
  }

  register() {
    if (this.form.valid) {
      return this.router.navigateByUrl('');
      /*
      this.authService.login(this.form.value.email, this.form.value.password)
        .subscribe(
          () => {
            console.log('User is logged in');
            this.router.navigateByUrl('/datasets');
          }
        );*/
    }
  }

  ngOnInit(): void {
  }


}
