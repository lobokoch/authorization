/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {MessageService} from 'primeng/api';

import { Tenant } from './tenant.model';
import { TenantService } from './tenant.service';
import { SecurityAuthorizationTranslationService } from './../i18n/security-authorization-translation.service';


@Component({
  selector: 'app-crud-tenant.component',
  templateUrl: './crud-tenant.component.html',
  styleUrls: ['./crud-tenant.component.css']
})

export class TenantComponent implements OnInit {
	tenant = new Tenant();
	
	constructor(
	    private tenantService: TenantService,
	    private securityAuthorizationTranslationService: SecurityAuthorizationTranslationService,
	    private route: ActivatedRoute,
	    private messageService: MessageService
	) { 
	}
	
	ngOnInit() {
	    const id = this.route.snapshot.params['id'];
	    if (id) {
	      this.getTenantById(id);
	    }
	}
	
	begin(form: FormControl) {
	    form.reset();
	    setTimeout(function() {
	      this.tenant = new Tenant();
	    }.bind(this), 1);
	}
	
	validateAllFormFields(form: FormGroup) {
	    Object.keys(form.controls).forEach(field => {
	      const control = form.get(field);
	
	      if (control instanceof FormControl) {
	        control.markAsDirty({ onlySelf: true });
	      } else if (control instanceof FormGroup) {
	        this.validateAllFormFields(control);
	      }
	    });
	}
	
	save(form: FormGroup) {
		if (!form.valid) {
	      this.validateAllFormFields(form);
	      return;
	    }
		    
	    if (this.isEditing) {
	      this.update();
	    } else {
	      this.create();
	    }
	}
	
	create() {
		
	    this.tenantService.create(this.tenant)
	    .then((tenant) => {
	      this.tenant = tenant;
	      this.showSuccess('Registro criado com sucesso!');
	    }).
	    catch(error => {
	      this.showError('Erro ao criar registro: ' + error);
	    });
	}
	
	update() {
	    this.tenantService.update(this.tenant)
	    .then((tenant) => {
	      this.tenant = tenant;
	      this.showSuccess('Registro alterado!');
	    })
	    .catch(error => {
	      this.showError('Erro ao atualizar registro: ' + error);
	    });
	}
	
	getTenantById(id: string) {
	    this.tenantService.retrieve(id)
	    .then((tenant) => this.tenant = tenant)
	    .catch(error => {
	      this.showError('Erro ao buscar registro: ' + id);
	    });
	}
	
	get isEditing() {
	    return Boolean(this.tenant.id);
	}
	
	
	
	
	
	public showSuccess(msg: string) {
	    this.messageService.add({severity: 'success', summary: 'Successo', detail: msg});
	}
	
	public showError(msg: string) {
	    this.messageService.add({severity: 'error', summary: 'Erro', detail: msg});
	}
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.securityAuthorizationTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	
	
}
