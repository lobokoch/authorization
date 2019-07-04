/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { HttpClientWithToken } from '../security/http-client-token';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserAccount, AccountCreatedDTO, SysUser } from './newaccount/useraccount.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class UserAccountService {

	url = environment.apiUrl + '/account';

	constructor(
    private http: HttpClientWithToken
  ) { }

	createAccount(userAccount: UserAccount): Promise<AccountCreatedDTO> {
	    return this.http.post<AccountCreatedDTO>(`${this.url}/createAccount`, userAccount)
	    .toPromise()
	    .then(response => {
        console.log('response: ' + response);
	      return response;
	    });
  }

	confirmAccount(id: String): Promise<SysUser> {
	    return this.http.put<SysUser>(`${this.url}/confirmAccount/${id}`, {})
	    .toPromise()
	    .then(response => {
        console.log('response: ' + response);
        const confirmedUser = response as SysUser;
        console.log('confirmedUser: ' + confirmedUser);
	      return confirmedUser;
	    });
	}
}

