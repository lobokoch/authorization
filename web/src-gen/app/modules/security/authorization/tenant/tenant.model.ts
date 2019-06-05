/**********************************************************************************************
Code generated with MKL Plug-in version: 3.6.2
Code generated at time stamp: 2019-06-05T06:36:41.347
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

export class TenantListFilter extends PaginationFilter {
	
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
