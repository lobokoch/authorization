/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:36:41.347
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import {ConfirmationService, LazyLoadEvent, SelectItem} from 'primeng/api';
import { Dropdown } from 'primeng/dropdown';
import * as moment from 'moment';
import { MessageHandlerService } from 'src/app/core/message-handler.service';

import { SysUserService } from './sysuser.service';
import { SecurityAuthorizationTranslationService } from './../i18n/security-authorization-translation.service';
import { SysUser } from './sysuser.model';
import { SysUserListFilter } from './sysuser.model';
import { SortField } from './sysuser.model';

import { TenantAutoComplete } from './../tenant/tenant.model';

@Component({
  selector: 'app-list-sysuser.component',
  templateUrl: './list-sysuser.component.html',
  styleUrls: ['./list-sysuser.component.css']
})

export class SysUserListComponent implements OnInit {
	
	sysUserListItems: SysUser[];
	sysUserListTotalElements = 0;
	sysUserListFilter = new SysUserListFilter();
	
	
	
	/*
	sysUser: SysUser;
	totaisFiltroContaPagar = new TotaisFiltroContaPagar(0.0, 0.0);
	mostrarDialogPagarConta = false;
	*/
	
	constructor(
	    private sysUserService: SysUserService,
	    private securityAuthorizationTranslationService: SecurityAuthorizationTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
    	this.sysUserListFilter.sortField = new SortField('name', 1); // asc
	    // this.sysUser = new SysUser();
        // this.contaPagar.dataPagamento = moment().toDate();
	}
	
	sysUserList(pageNumber = 0) {
	    this.sysUserListFilter.pageNumber = pageNumber;
	    this.sysUserService
	    .sysUserList(this.sysUserListFilter)
	    .then(result => {
	      	this.sysUserListItems = result.items;
	      	this.sysUserListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	sysUserFilterSearch() {
	    this.sysUserList(0);
	}
	
	deleteSysUser(sysUser: SysUser) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.sysUserService.delete(sysUser.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.sysUserList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	sysUserListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.sysUserListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.sysUserListFilter.sortField = new SortField('name', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.sysUserList(pageNumber);
	}
	
	
	sysUserTenantAutoCompleteFieldConverter(tenant: TenantAutoComplete) {
		if (tenant) {
			return tenant.name;
		} else {
			return null;
		}
	}
	
	
	
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.securityAuthorizationTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	/*********************
	getContaCssClass(conta: ContaPagar): string {
	    const vencimento = conta.dataVencimento;
	    const emAberto = conta.dataPagamento == null;
	    const hoje = moment();
	    if (vencimento && emAberto) {
	      if (moment(vencimento).isBefore(hoje, 'day')) {
	        return 'conta-vencida';
	      }
	      if (moment(vencimento).isSame(hoje, 'day')) {
	        return 'conta-vence-hoje';
	      }
	      if (moment(vencimento).isSame(moment().add(1, 'day'), 'day')) {
	        return 'conta-vence-amanha';
	      }
	      if (moment(vencimento).isBefore(moment().add(1, 'week').startOf('week'), 'day')) {
	        return 'conta-vence-essa-semana';
	      }
	    }
	    return 'conta-ok';
	}
	
	get getTotalGeralContasPagar(): number {
	    const total = this.totaisFiltroContaPagar.totalValorPagar - this.totaisFiltroContaPagar.totalValorPago;
	    return total ? total : 0.0;
	}
	  
	get getTotalValorPagar(): number {
	    const total = this.totaisFiltroContaPagar.totalValorPagar;
	    return total ? total : 0.0;
	}
	
	get getTotalValorPago(): number {
		const total = this.totaisFiltroContaPagar.totalValorPago;
		return total ? total : 0.0;
	}
	
	getTotaisFiltroContaPagar() {
	    this.contasPagarService.getTotaisFiltroContaPagar(this.contaPagarListFilter)
	    .then(response => {
	      this.totaisFiltroContaPagar = response;
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao buscar totais:' + erro);
	    });
	}
	
	mostrarPagarConta(conta: ContaPagar) {
	    this.contaPagar = new ContaPagar();
	    this.contaPagar.assign(conta);
	    // this.contaPagar.dataPagamento = new Date(this.contaPagar.dataPagamento);
	    const data = this.contaPagar.dataPagamento;
	    if (data == null) {
	      this.contaPagar.dataPagamento = moment().toDate();
	    } else {
	      this.contaPagar.dataPagamento = moment(this.contaPagar.dataPagamento).toDate();
	    }
	    if (!this.contaPagar.valorPago || this.contaPagar.valorPago === 0) {
	      this.contaPagar.valorPago = conta.valor;
	    }
	    this.mostrarDialogPagarConta = true;
	}
	
	cancelarPagarConta() {
		this.mostrarDialogPagarConta = false;
	}
	
	executarPagarConta() {
	    this.contasPagarService.update(this.contaPagar)
	    .then((contaPagar) => {
	      this.mostrarDialogPagarConta = false;
	      this.messageHandler.showSuccess(`A conta ${contaPagar.descricao} foi paga.`);
	      this.contaPagarList(0);
	    })
	    .catch(erro => {
	      this.messageHandler.showError('Erro ao pagar a conta: ' + erro);
	    });
	}
	*********************/
}
