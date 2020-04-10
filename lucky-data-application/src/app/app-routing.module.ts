import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from './component/login-form/login-form.component';
import {DatasetListComponent} from './component/dataset-list/dataset-list.component';
import { RegisterFormComponent } from './component/register-form/register-form.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';


const routes: Routes = [
  { path: '', component: LoginFormComponent },
  { path: 'datasets', component: DatasetListComponent },
  { path: 'inscription', component: RegisterFormComponent },
  { path: 'mot-de-passe-oublie', component: ForgotPasswordComponent }
  ]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
