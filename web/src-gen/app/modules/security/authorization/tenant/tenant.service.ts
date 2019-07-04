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

import { Tenant } from './tenant.model';
import { TenantAutoComplete } from './tenant.model';
import { TenantListFilter } from './tenant.model';
import { TenantNameAutoComplete } from './tenant.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class TenantService {
	
	url = environment.apiUrl + '/security/authorization/entities/tenant';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(tenant: Tenant): Promise<Tenant> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, tenant, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as Tenant;
	      return created;
	    });
	}
	
	update(tenant: Tenant): Promise<Tenant> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${tenant.id}`, tenant, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as Tenant;
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<Tenant> {
	    const headers = this.getHeaders();
	    return this.http.get<Tenant>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const tenant = response as Tenant;
	      return tenant;
	    });
	}
	
	
	
	
	autoComplete(query: string): Promise<TenantAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<TenantAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as TenantAutoComplete[];
	        return result;
	      });
	
	}
	
				
	
	tenantNameAutoComplete(query: string): Promise<any> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<any>(`${this.url}/tenantNameAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as TenantNameAutoComplete[];
	        return result;
	      });
	
	}
	
	tenantList(tenantListFilter: TenantListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(tenantListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of Tenant */
	        const totalElements = data.totalElements;
	
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	mountAndGetSearchParams(filter: TenantListFilter): HttpParams {
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
	replicateTenant(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateTenantPayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateTenant`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterTenant(filter: TenantrListFilter): Promise<TotaisfilterTenant> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterTenant>(`${this.url}/getTotaisfilterTenant`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterTenant;
	      return result;
	    });
	}
	*/
}

