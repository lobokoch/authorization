INSERT INTO tenant (id, "name", active, max_users, balance)
VALUES('c673b595-992f-4a23-9cb8-c2a948c97eb7', 'kerubinplatformgmailcom', true, 2, 0.0);


INSERT INTO sys_user (id,"name",email,"password",account_type,tenant,administrator,active,activation_date,confirmed,confirmation_date,confirmation_id,created_by,created_date,last_modified_by,last_modified_date) VALUES 
('0d99d484-755f-4482-b4ba-64da363596ed','Admin','admin','$2a$10$xO99TFPoDDSDGPUg8/dEjOU2fSI/UX9bUVjFi.DA7ZrHhRcl5nPqK','PERSONAL','c673b595-992f-4a23-9cb8-c2a948c97eb7',true,true,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL)
,('f53c0a3d-1de1-4498-8e48-59266fa8cc57','Kerubin Anonymous','anonymous@kerubin.com.br','$2a$10$5IA1P/gi2wm2qVDlNPeR0u4y5Os8goPXSvP730nqBrfFgMdZ9mr0e','CORPORATE','c673b595-992f-4a23-9cb8-c2a948c97eb7',false,true,NULL,false,NULL,NULL,NULL,NULL,NULL,NULL)
;