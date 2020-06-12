import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './interceptor/auth.interceptor';
import {JwtInterceptor} from './interceptor/jwt.interceptor';
import { LoginFormComponent } from './component/login-form/login-form.component';
import { RegisterFormComponent } from './component/register-form/register-form.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import {ReactiveFormsModule} from '@angular/forms';
import { DatasetListComponent } from './component/dataset-list/dataset-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MyMaterialModule} from './modules/material.module';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { FooterComponent } from './component/footer/footer.component';
import {ProfileComponent} from "./component/profile/profile.component";
import { NavbarComponent } from './component/navbar/navbar.component';
import { DatasetDetailsComponent } from './component/dataset-details/dataset-details.component';
import { DatasetTileComponent } from './component/dataset-tile/dataset-tile.component';
import { DatasetUploadComponent } from './component/dataset-upload/dataset-upload.component';
import { AdminUserComponent } from './component/admin-user/admin-user.component';
import { FileControlValueAccessor } from './accessor/file-control-value-accessor';
import { AdminDatasetComponent } from './component/admin-dataset/admin-dataset.component';
import { TermsOfUseComponent } from './component/terms-of-use/terms-of-use.component';
import { PrivacyPolicyComponent } from './component/privacy-policy/privacy-policy.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    DatasetListComponent,
    RegisterFormComponent,
    ForgotPasswordComponent,
    FooterComponent,
    ProfileComponent,
    NavbarComponent,
    DatasetDetailsComponent,
    DatasetTileComponent,
    DatasetUploadComponent,
    AdminUserComponent,
    FileControlValueAccessor,
    AdminDatasetComponent,
    TermsOfUseComponent,
    PrivacyPolicyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MyMaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    MatDatepickerModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
