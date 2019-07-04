/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-kerubin-menu',
  templateUrl: './kerubin-menu.component.html',
  styleUrls: ['./kerubin-menu.component.css']
})
export class KerubinMenuComponent implements OnInit {

  items: MenuItem[];


  constructor() { }

  ngOnInit() {
    this.loadMenu();
  }

  loadMenu() {
    this.items = [

      {
      	label: 'Segurança',
      	icon: 'pi pi-pw',
      	items: [
      		
      		{
      			label: 'Autorização',
      			icon: 'pi pi-fw ',
      			items: [
      				{ label: 'Usuário', icon: 'pi pi-fw', routerLink: '/sysuser' }, 
      				{ label: 'Tenant', icon: 'pi pi-fw', routerLink: '/tenant' }
      			]
      		}
      		
      	]
      }


    ];
  }

}
