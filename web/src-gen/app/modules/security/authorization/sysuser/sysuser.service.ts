/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import * as moment from 'moment';

import { HttpClientWithToken } from '../../../../security/http-client-token';

import { SysUser } from './sysuser.model';
import { SysUserAutoComplete } from './sysuser.model';
import { Tenant } from './../tenant/tenant.model';
import { SysUserListFilter } from './sysuser.model';
import { SysUserNameAutoComplete } from './sysuser.model';
import { environment } from 'src/environments/environment';
import { TenantAutoComplete } from './../tenant/tenant.model';

@Injectable()
export class SysUserService {
	
	url = environment.apiUrl + '/security/authorization/entities/sysUser';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(sysUser: SysUser): Promise<SysUser> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, sysUser, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as SysUser;
	      this.adjustNullEntitySlots([created]);
	      this.adjustEntityDates([created]);
	      return created;
	    });
	}
	
	update(sysUser: SysUser): Promise<SysUser> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${sysUser.id}`, sysUser, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as SysUser;
	      this.adjustNullEntitySlots([updated]);
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<SysUser> {
	    const headers = this.getHeaders();
	    return this.http.get<SysUser>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const sysUser = response as SysUser;
	      this.adjustNullEntitySlots([sysUser]);
	      this.adjustEntityDates([sysUser]);
	      return sysUser;
	    });
	}
	
	
	private adjustEntityDates(entityList: SysUser[]) {
		entityList.forEach(sysUser => {
		      if (sysUser.activationDate) {
		        sysUser.activationDate = moment(sysUser.activationDate, 'YYYY-MM-DD H:m:s').toDate();
		      }
		      	
		      
		      if (sysUser.confirmationDate) {
		        sysUser.confirmationDate = moment(sysUser.confirmationDate, 'YYYY-MM-DD H:m:s').toDate();
		      }
		      	
		});
	}
	
	private adjustNullEntitySlots(entityList: SysUser[]) {
		/*entityList.forEach(sysUser => {
		      if (!sysUser.tenant) {
		        sysUser.tenant = new Tenant();
		      }
		      	
		});*/
	}
	
	autoComplete(query: string): Promise<SysUserAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<SysUserAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as SysUserAutoComplete[];
	        return result;
	      });
	
	}
	
							
	// Begin relationships autoComplete 
	
	tenantTenantAutoComplete(query: string): Promise<TenantAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<TenantAutoComplete[]>(`${this.url}/tenantTenantAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as TenantAutoComplete[];
	        return result;
	      });
	
	}
	
	// End relationships autoComplete
	
				
	
	sysUserNameAutoComplete(query: string): Promise<any> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<any>(`${this.url}/sysUserNameAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as SysUserNameAutoComplete[];
	        return result;
	      });
	
	}
	
	sysUserList(sysUserListFilter: SysUserListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(sysUserListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of SysUser */
	        const totalElements = data.totalElements;
	
	        this.adjustNullEntitySlots(items);
	        this.adjustEntityDates(items);
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	mountAndGetSearchParams(filter: SysUserListFilter): HttpParams {
	    let params = new HttpParams();
	    if (filter.pageNumber) {
	      params = params.set('page', filter.pageNumber.toString());
	    }
	
	    if (filter.pageSize) {
	      params = params.set('size', filter.pageSize.toString());
	    }
		
		// name
		if (filter.name) {
			const name = filter.name.map(item => item.name).join(',');
			params = params.set('name', name);
		}
	
	    // Sort
	    if (filter.sortField) {
	      // search/nameStartsWith?name=K&sort=name,desc
	      const sortField = filter.sortField;
	      const sortValue = `${sortField.field},${sortField.order > 0 ? 'asc' : 'desc'}`;
	      params = params.set('sort', sortValue);
	    }
	
	    return params;
	  }
	
	dateToStr(data: Date): string {
	    return moment(data).format('YYYY-MM-DD');
	}
	
	/*** TODO: avaliar se vai ser feito isso.
	replicateSysUser(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateSysUserPayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateSysUser`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterSysUser(filter: SysUserrListFilter): Promise<TotaisfilterSysUser> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterSysUser>(`${this.url}/getTotaisfilterSysUser`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterSysUser;
	      return result;
	    });
	}
	*/
}

