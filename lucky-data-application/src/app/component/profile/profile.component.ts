import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {UserService} from '../../service/user.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass']
})

export class ProfileComponent implements OnInit {

  user: User;
  form: FormGroup;
  submitted = false;
  hide = true;
  hide2 = true;
  updateError = false;
  errorMess = '';
  updateSuccess = false;
  successMess = '';
  formEnable = false;
  buttonText = 'Modifier mon profil';

  constructor(private fb: FormBuilder, private userService: UserService){

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
      email: ['', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@lcl.fr$')
      ])],
      password: ['', Validators.compose([
        Validators.pattern(/^(?=\D*\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]).{8,30}$/)
      ])],
      passwordConfirm: '',
    }, {
      validator: [
        this.MustMatch('password', 'passwordConfirm')
      ]
    });

    this.getUserInfo();

  }

  getUserInfo(){
    this.userService.getUserInfo().subscribe(user => {
      this.user = user;
      this.form.setValue({
          firstName : user.firstName,
          lastName : user.lastName,
          email : user.email,
          password : '',
          passwordConfirm : ''
        }
      );
      this.form.disable();
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

  ChangeStateForm(){
    if (this.form.enabled){
      this.form.disable();
      this.formEnable = false;
      this.buttonText = 'Modifier mon profil';
      this.form.setValue({
          firstName : this.user.firstName,
          lastName : this.user.lastName,
          email : this.user.email,
          password : '',
          passwordConfirm : ''
        }
      );
    } else {
      this.form.enable();
      this.formEnable = true;
      this.buttonText = 'Annuler';
    }
  }

  update(){
    this.submitted = true;
    if (this.form.valid) {
      let firstName = null;
      let lastName = null;
      let email = null;
      let password = null;
      if (this.form.value.firstName !== this.user.firstName) { firstName = this.form.value.firstName; }
      if (this.form.value.lastName !== this.user.lastName) { lastName = this.form.value.lastName; }
      if (this.form.value.email !== this.user.email) { email = this.form.value.email; }
      if (this.form.value.password !== '') { password = this.form.value.password; }
      this.userService.updateUser(firstName, lastName, email, password)
        .subscribe(
          () => {
            console.log('user updated');
            this.updateSuccess = true;
            this.successMess = 'Vos informations ont bien été modifiées';
            this.getUserInfo();
          },
          (error) => {
            console.log(error);
            this.updateError = true;
            this.errorMess = 'Une erreur interne est survenue, veuillez réessayer';
          });
      this.ChangeStateForm();
    }
  }

  ngOnInit(): void {}

}
