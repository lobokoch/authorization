/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { MenuModule } from 'primeng/menu';
import { KerubinMenuComponent } from './kerubin-menu.component';
import { PanelMenuModule } from 'primeng/panelmenu';
import { NgModule } from '@angular/core';

@NgModule({

  imports: [
    PanelMenuModule,
    MenuModule
  ],

  declarations: [
    KerubinMenuComponent
  ],

  exports: [
    KerubinMenuComponent,
    PanelMenuModule,
    MenuModule
  ]

})

export class KerubinMenuModule {  }

