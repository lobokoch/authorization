/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:36:41.347
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { TenantComponent } from './crud-tenant.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { TenantListComponent } from './list-tenant.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'tenant', loadChildren: './modules/security/authorization/tenant/tenant.module#TenantModule' }
  {
    path: '',
    component: TenantListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: TenantComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: TenantComponent,
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

export class TenantRoutingModule { }
