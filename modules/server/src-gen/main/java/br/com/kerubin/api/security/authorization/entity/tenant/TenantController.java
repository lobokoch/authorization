/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

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

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("security/authorization/entities/tenant")
public class TenantController {
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	TenantDTOConverter tenantDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Tenant> create(@Valid @RequestBody Tenant tenant) {
		TenantEntity tenantEntity = tenantService.create(tenantDTOConverter.convertDtoToEntity(tenant));
		return ResponseEntity.status(HttpStatus.CREATED).body(tenantDTOConverter.convertEntityToDto(tenantEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Tenant> read(@PathVariable java.util.UUID id) {
		try {
			TenantEntity tenantEntity = tenantService.read(id);
			return ResponseEntity.ok(tenantDTOConverter.convertEntityToDto(tenantEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Tenant> update(@PathVariable java.util.UUID id, @Valid @RequestBody Tenant tenant) {
		try {
			TenantEntity tenantEntity = tenantService.update(id, tenantDTOConverter.convertDtoToEntity(tenant));
			return ResponseEntity.ok(tenantDTOConverter.convertEntityToDto(tenantEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		tenantService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<Tenant> list(TenantListFilter tenantListFilter, Pageable pageable) {
		Page<TenantEntity> page = tenantService.list(tenantListFilter, pageable);
		List<Tenant> content = page.getContent().stream().map(pe -> tenantDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<Tenant> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<TenantAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<TenantAutoComplete> result = tenantService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/tenantNameAutoComplete")
	public Collection<TenantNameAutoComplete> tenantNameAutoComplete(@RequestParam("query") String query) {
		Collection<TenantNameAutoComplete> result = tenantService.tenantNameAutoComplete(query);
		return result;
	}
	
	
}
