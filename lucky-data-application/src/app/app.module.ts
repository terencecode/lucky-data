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
import { FooterComponent } from './component/footer/footer.component';
import {ProfileComponent} from "./component/profile/profile.component";
import { NavbarComponent } from './component/navbar/navbar.component';
import { DatasetDetailsComponent } from './component/dataset-details/dataset-details.component';
import { DatasetTileComponent } from './component/dataset-tile/dataset-tile.component';

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
    DatasetTileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MyMaterialModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
