/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { SysUserComponent } from './crud-sysuser.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { SysUserListComponent } from './list-sysuser.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'sysuser', loadChildren: './modules/security/authorization/sysuser/sysuser.module#SysUserModule' }
  {
    path: '',
    component: SysUserListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: SysUserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: SysUserComponent,
    canActivate: [AuthGuard]
  }
];


@NgModule({

  imports: [
    RouterModule.forChild(routes)
  ],

  exports: [
    RouterModule
  ]

})

export class SysUserRoutingModule { }
