import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from './component/login-form/login-form.component';
import {DatasetListComponent} from './component/dataset-list/dataset-list.component';
import { RegisterFormComponent } from './component/register-form/register-form.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import {ProfileComponent} from "./component/profile/profile.component";


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginFormComponent },
  { path: 'datasets', component: DatasetListComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'password-forgotten', component: ForgotPasswordComponent },
  {path: 'profile', component: ProfileComponent}
  ]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
