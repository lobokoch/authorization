package br.com.kerubin.api.security.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.security.authorization.entity.service.CustomSysUserServiceImpl;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUser;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserDTOConverter;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;

@RestController
@RequestMapping("/security/authorization/entities/sysUser")
public class SecurityController {
	
	@Inject
	private CustomSysUserServiceImpl securiryService;
	
	@Inject
	SysUserDTOConverter sysUserDTOConverter;
	
	@Transactional(readOnly = true)
	@GetMapping("/listActiveUsers")
	public List<SysUser> listActiveUsers() {
		List<SysUserEntity> users = securiryService.listActiveUsers();
		List<SysUser> result = users.stream().map(it -> sysUserDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		return result;
	}

}
