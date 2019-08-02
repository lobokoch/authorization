/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS sys_user CASCADE;
DROP TABLE IF EXISTS tenant CASCADE;
DROP TABLE IF EXISTS tenant_op_count CASCADE;
**********************************************************/

CREATE TABLE sys_user /* SysUser */  (
	id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	active BOOLEAN DEFAULT false,
	administrator BOOLEAN DEFAULT false,
	account_type VARCHAR(255) NOT NULL /* accountType */,
	tenant UUID,
	activation_date TIMESTAMP /* activationDate */,
	confirmed BOOLEAN DEFAULT false,
	confirmation_date TIMESTAMP /* confirmationDate */,
	confirmation_id VARCHAR(255) /* confirmationId */,
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

CREATE TABLE tenant /* Tenant */  (
	id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	max_users NUMERIC(19) NOT NULL /* maxUsers */,
	balance DECIMAL NOT NULL,
	active BOOLEAN DEFAULT false
);

CREATE TABLE tenant_op_count /* TenantOpCount */  (
	id UUID NOT NULL,
	description VARCHAR(255),
	tenant UUID NOT NULL,
	day_op NUMERIC(19) NOT NULL /* dayOp */,
	month_op NUMERIC(19) NOT NULL /* monthOp */,
	year_op NUMERIC(19) NOT NULL /* yearOp */,
	count_get NUMERIC(19) NOT NULL /* countGet */,
	count_post NUMERIC(19) NOT NULL /* countPost */,
	count_put NUMERIC(19) NOT NULL /* countPut */,
	count_delete NUMERIC(19) NOT NULL /* countDelete */,
	count_list NUMERIC(19) NOT NULL /* countList */,
	count_auto_complete NUMERIC(19) NOT NULL /* countAutoComplete */,
	count_op NUMERIC(19) NOT NULL /* countOp */
);

/* PRIMARY KEYS */
ALTER TABLE sys_user ADD CONSTRAINT pk_sys_user_id PRIMARY KEY (id);
ALTER TABLE tenant ADD CONSTRAINT pk_tenant_id PRIMARY KEY (id);
ALTER TABLE tenant_op_count ADD CONSTRAINT pk_tenant_op_count_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE sys_user ADD CONSTRAINT fk_sys_user_tenant FOREIGN KEY (tenant) REFERENCES tenant (id);
ALTER TABLE tenant_op_count ADD CONSTRAINT fk_tenant_op_count_tenant FOREIGN KEY (tenant) REFERENCES tenant (id);

