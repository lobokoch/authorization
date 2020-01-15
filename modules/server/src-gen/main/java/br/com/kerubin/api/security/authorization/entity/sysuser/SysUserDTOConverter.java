/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.security.authorization.entity.sysuser;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class SysUserDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public SysUser convertEntityToDto(SysUserEntity entity) {
		SysUser dto = null;
		if (entity != null) {
			dto = mapper.map(entity, SysUser.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public SysUserEntity convertDtoToEntity(SysUser dto) {
		SysUserEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, SysUserEntity.class);
		}
		return entity;
	}


}