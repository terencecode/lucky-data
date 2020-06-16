import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from './component/login-form/login-form.component';
import {DatasetListComponent} from './component/dataset-list/dataset-list.component';
import { RegisterFormComponent } from './component/register-form/register-form.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import {ProfileComponent} from './component/profile/profile.component';
import {DatasetDetailsComponent} from "./component/dataset-details/dataset-details.component";
import {DatasetUploadComponent} from "./component/dataset-upload/dataset-upload.component";
import {AdminUserComponent} from './component/admin-user/admin-user.component';
import {AdminDatasetComponent} from './component/admin-dataset/admin-dataset.component';
import {TermsOfUseComponent} from './component/terms-of-use/terms-of-use.component';
import {PrivacyPolicyComponent} from './component/privacy-policy/privacy-policy.component';
import {ModelListComponent} from "./component/model-list/model-list.component";
import {ModelDetailsComponent} from "./component/model-details/model-details.component";
import {ModelUploadComponent} from "./component/model-upload/model-upload.component";


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'forgotten-password', component: ForgotPasswordComponent },
  { path: 'terms-of-use', component: TermsOfUseComponent },
  { path: 'privacy-policy', component: PrivacyPolicyComponent },
  { path: 'datasets', component: DatasetListComponent },
  { path: 'models', component: ModelListComponent },
  { path: 'dataset/:datasetId', component: DatasetDetailsComponent},
  { path: 'model/:modelId', component: ModelDetailsComponent},
  { path: 'profile', component: ProfileComponent },
  { path: 'upload/dataset', component: DatasetUploadComponent},
  { path: 'upload/model', component: ModelUploadComponent},
  { path: 'admin/users', component: AdminUserComponent},
  { path: 'admin/datasets', component: AdminDatasetComponent}
  ]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
