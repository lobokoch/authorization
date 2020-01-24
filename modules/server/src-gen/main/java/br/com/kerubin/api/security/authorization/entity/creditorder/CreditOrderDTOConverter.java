/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.security.authorization.entity.creditorder;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class CreditOrderDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public CreditOrder convertEntityToDto(CreditOrderEntity entity) {
		CreditOrder dto = null;
		if (entity != null) {
			dto = mapper.map(entity, CreditOrder.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public CreditOrderEntity convertDtoToEntity(CreditOrder dto) {
		CreditOrderEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CreditOrderEntity.class);
		}
		return entity;
	}


}