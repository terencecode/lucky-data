import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./interceptor/auth.interceptor";
import {JwtInterceptor} from "./interceptor/jwt.interceptor";
import { LoginFormComponent } from './component/login-form/login-form.component';
import {ReactiveFormsModule} from "@angular/forms";
import { DatasetListComponent } from './component/dataset-list/dataset-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    DatasetListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
