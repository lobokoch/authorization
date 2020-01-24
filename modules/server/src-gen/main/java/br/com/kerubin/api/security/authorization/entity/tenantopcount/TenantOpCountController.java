/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

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
@RequestMapping("security/authorization/entities/tenantOpCount")
public class TenantOpCountController {
	
	@Autowired
	private TenantOpCountService tenantOpCountService;
	
	@Autowired
	TenantOpCountDTOConverter tenantOpCountDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<TenantOpCount> create(@Valid @RequestBody TenantOpCount tenantOpCount) {
		TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.create(tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
		return ResponseEntity.status(HttpStatus.CREATED).body(tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<TenantOpCount> read(@PathVariable java.util.UUID id) {
		try {
			TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.read(id);
			return ResponseEntity.ok(tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TenantOpCount> update(@PathVariable java.util.UUID id, @Valid @RequestBody TenantOpCount tenantOpCount) {
		try {
			TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.update(id, tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
			return ResponseEntity.ok(tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		tenantOpCountService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<TenantOpCount> list(TenantOpCountListFilter tenantOpCountListFilter, Pageable pageable) {
		Page<TenantOpCountEntity> page = tenantOpCountService.list(tenantOpCountListFilter, pageable);
		List<TenantOpCount> content = page.getContent().stream().map(pe -> tenantOpCountDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<TenantOpCount> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<TenantOpCountAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<TenantOpCountAutoComplete> result = tenantOpCountService.autoComplete(query);
		return result;
	}
	
	
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/tenantTenantAutoComplete")
	public Collection<TenantAutoComplete> tenantTenantAutoComplete(@RequestParam("query") String query) {
		Collection<TenantAutoComplete> result = tenantOpCountService.tenantTenantAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
