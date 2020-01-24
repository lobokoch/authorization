/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.security.authorization.entity.tenant;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class TenantDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public Tenant convertEntityToDto(TenantEntity entity) {
		Tenant dto = null;
		if (entity != null) {
			dto = mapper.map(entity, Tenant.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public TenantEntity convertDtoToEntity(Tenant dto) {
		TenantEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, TenantEntity.class);
		}
		return entity;
	}


}