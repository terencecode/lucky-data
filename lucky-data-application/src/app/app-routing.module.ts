import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from "./component/login-form/login-form.component";
import {DatasetListComponent} from "./component/dataset-list/dataset-list.component";


const routes: Routes = [
  { path: '', component: LoginFormComponent },
  { path: 'datasets', component: DatasetListComponent }]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
