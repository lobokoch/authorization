/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.com.kerubin.api.security.authorization.common.PageResult;

		
import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("security/authorization/entities/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	SysUserDTOConverter sysUserDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<SysUser> create(@Valid @RequestBody SysUser sysUser) {
		SysUserEntity sysUserEntity = sysUserService.create(sysUserDTOConverter.convertDtoToEntity(sysUser));
		return ResponseEntity.status(HttpStatus.CREATED).body(sysUserDTOConverter.convertEntityToDto(sysUserEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<SysUser> read(@PathVariable java.util.UUID id) {
		try {
			SysUserEntity sysUserEntity = sysUserService.read(id);
			return ResponseEntity.ok(sysUserDTOConverter.convertEntityToDto(sysUserEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<SysUser> update(@PathVariable java.util.UUID id, @Valid @RequestBody SysUser sysUser) {
		try {
			SysUserEntity sysUserEntity = sysUserService.update(id, sysUserDTOConverter.convertDtoToEntity(sysUser));
			return ResponseEntity.ok(sysUserDTOConverter.convertEntityToDto(sysUserEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		sysUserService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<SysUser> list(SysUserListFilter sysUserListFilter, Pageable pageable) {
		Page<SysUserEntity> page = sysUserService.list(sysUserListFilter, pageable);
		List<SysUser> content = page.getContent().stream().map(pe -> sysUserDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<SysUser> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<SysUserAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<SysUserAutoComplete> result = sysUserService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/sysUserNameAutoComplete")
	public Collection<SysUserNameAutoComplete> sysUserNameAutoComplete(@RequestParam("query") String query) {
		Collection<SysUserNameAutoComplete> result = sysUserService.sysUserNameAutoComplete(query);
		return result;
	}
	
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/tenantTenantAutoComplete")
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(@RequestParam("query") String query) {
		Collection<TenantAutoComplete> result = sysUserService.tenantTenantAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}