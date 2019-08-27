/**************** WARNING WILL DELETE ALL TABLES *********
DROP TABLE IF EXISTS sys_user CASCADE;
DROP TABLE IF EXISTS tenant CASCADE;
DROP TABLE IF EXISTS tenant_op_count CASCADE;
DROP TABLE IF EXISTS credit_order CASCADE;
**********************************************************/

CREATE TABLE sys_user /* SysUser */  (
	id UUID NOT NULL,
	name VARCHAR(255) NOT NULL,
	cnpj_cpf VARCHAR(20) /* cnpjCPF */,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	active BOOLEAN DEFAULT false,
	administrator BOOLEAN DEFAULT false,
	super_administrator BOOLEAN DEFAULT false /* superAdministrator */,
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
	year_op NUMERIC(19) NOT NULL /* yearOp */,
	month_op NUMERIC(19) NOT NULL /* monthOp */,
	day_op NUMERIC(19) NOT NULL /* dayOp */,
	hour_op NUMERIC(19) NOT NULL /* HourOp */,
	count_get NUMERIC(19) NOT NULL /* countGet */,
	count_post NUMERIC(19) NOT NULL /* countPost */,
	count_put NUMERIC(19) NOT NULL /* countPut */,
	count_delete NUMERIC(19) NOT NULL /* countDelete */,
	count_list NUMERIC(19) NOT NULL /* countList */,
	count_auto_complete NUMERIC(19) NOT NULL /* countAutoComplete */,
	count_op NUMERIC(19) NOT NULL /* countOp */
);

CREATE TABLE credit_order /* creditOrder */  (
	id UUID NOT NULL,
	order_user_name VARCHAR(255) NOT NULL /* orderUserName */,
	order_tenant_name VARCHAR(255) NOT NULL /* orderTenantName */,
	order_user UUID NOT NULL /* orderUser */,
	order_date DATE NOT NULL /* orderDate */,
	order_value DECIMAL NOT NULL /* orderValue */,
	order_bonus_value DECIMAL NOT NULL /* orderBonusValue */,
	order_total_credits DECIMAL NOT NULL /* orderTotalCredits */,
	payment_method VARCHAR(255) NOT NULL /* paymentMethod */,
	payment_method_description VARCHAR(255) /* paymentMethodDescription */,
	order_status VARCHAR(255) NOT NULL /* orderStatus */,
	order_paid_date DATE /* orderPaidDate */,
	order_canceled_date DATE /* orderCanceledDate */,
	order_history VARCHAR(255) /* orderHistory */,
	created_by VARCHAR(255) /* createdBy */,
	created_date TIMESTAMP /* createdDate */,
	last_modified_by VARCHAR(255) /* lastModifiedBy */,
	last_modified_date TIMESTAMP /* lastModifiedDate */
);

/* PRIMARY KEYS */
ALTER TABLE sys_user ADD CONSTRAINT pk_sys_user_id PRIMARY KEY (id);
ALTER TABLE tenant ADD CONSTRAINT pk_tenant_id PRIMARY KEY (id);
ALTER TABLE tenant_op_count ADD CONSTRAINT pk_tenant_op_count_id PRIMARY KEY (id);
ALTER TABLE credit_order ADD CONSTRAINT pk_credit_order_id PRIMARY KEY (id);

/* FOREIGN KEYS */
ALTER TABLE sys_user ADD CONSTRAINT fk_sys_user_tenant FOREIGN KEY (tenant) REFERENCES tenant (id);
ALTER TABLE tenant_op_count ADD CONSTRAINT fk_tenant_op_count_tenant FOREIGN KEY (tenant) REFERENCES tenant (id);
ALTER TABLE credit_order ADD CONSTRAINT fk_credit_order_order_user FOREIGN KEY (order_user) REFERENCES sys_user (id);


/* INDEXES */
CREATE INDEX sysuser_name_lower_idx ON sys_user (lower(name));
CREATE INDEX sys_user_cnpj_cpf_idx ON sys_user (cnpj_cpf);
CREATE INDEX sys_user_email_idx ON sys_user (email);
CREATE INDEX tenant_name_lower_idx ON tenant (lower(name));
CREATE INDEX creditorder_orderusername_lower_idx ON credit_order (lower(order_user_name));
