/**********************************************************************************************
Code generated with MKL Plug-in version: 6.0.4
Code generated at time stamp: 2019-07-03T07:08:37.172
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


export class SortField {
  field: string;
  order: number;

  constructor(field: string, order: number) {
    this.field = field;
    this.order = order;
  }
}

export class PaginationFilter {
  pageNumber: number;
  pageSize: number;
  sortField: SortField;

  constructor() {
    this.pageNumber = 0;
    this.pageSize = 10;
  }
}

export class TenantNameAutoComplete {
	name: string;
}

export class TenantListFilter extends PaginationFilter {
	
	name: TenantNameAutoComplete[];
}

export class Tenant {
	id: string;
	name: string;
	active: boolean = false;
}

export class TenantAutoComplete {
	id: string;
	name: string;
}

export class TenantSumFields {
}
