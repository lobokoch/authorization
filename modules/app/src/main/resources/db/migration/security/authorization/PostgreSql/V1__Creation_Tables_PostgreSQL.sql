/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS sys_user CASCADE;
DROP TABLE IF EXISTS tenant CASCADE;
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
	active BOOLEAN DEFAULT false
);

/* PRIMARY KEYS */
ALTER TABLE sys_user ADD CONSTRAINT pk_sys_user_id PRIMARY KEY (id);
ALTER TABLE tenant ADD CONSTRAINT pk_tenant_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE sys_user ADD CONSTRAINT fk_sys_user_tenant FOREIGN KEY (tenant) REFERENCES tenant (id);

