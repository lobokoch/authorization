/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

// Angular
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { JwtModule } from '@auth0/angular-jwt';
import { RouterModule } from '@angular/router';

// Kerubin - BEGIN
import { LoginComponent } from './login/login.component';
import { AuthService } from './auth.service';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { AuthGuard } from './auth.guard';
import { environment } from 'src/environments/environment';
import { LogoutService } from './logout.service';
// Kerubin - END

export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  imports: [

    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: environment.tokenWhitelistedDomains,
        blacklistedRoutes: environment.tokenBlacklistedRoutes
      }
    }),

    CommonModule,
    FormsModule,
    InputTextModule,
    ButtonModule,
    CardModule,
    RouterModule
  ],

  declarations: [
    LoginComponent
  ],

  providers: [
    AuthGuard,
    AuthService,
    LogoutService
  ]
})

export class SecurityModule {

}

