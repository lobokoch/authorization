/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

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

		
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("security/authorization/entities/creditOrderAdmin")
public class CreditOrderAdminController {
	
	@Autowired
	private CreditOrderAdminService creditOrderAdminService;
	
	@Autowired
	CreditOrderAdminDTOConverter creditOrderAdminDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CreditOrderAdmin> create(@Valid @RequestBody CreditOrderAdmin creditOrderAdmin) {
		CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.create(creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
		return ResponseEntity.status(HttpStatus.CREATED).body(creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<CreditOrderAdmin> read(@PathVariable java.util.UUID id) {
		try {
			CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.read(id);
			return ResponseEntity.ok(creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<CreditOrderAdmin> update(@PathVariable java.util.UUID id, @Valid @RequestBody CreditOrderAdmin creditOrderAdmin) {
		try {
			CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.update(id, creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
			return ResponseEntity.ok(creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		creditOrderAdminService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<CreditOrderAdmin> list(CreditOrderAdminListFilter creditOrderAdminListFilter, Pageable pageable) {
		Page<CreditOrderAdminEntity> page = creditOrderAdminService.list(creditOrderAdminListFilter, pageable);
		List<CreditOrderAdmin> content = page.getContent().stream().map(pe -> creditOrderAdminDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<CreditOrderAdmin> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<CreditOrderAdminAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<CreditOrderAdminAutoComplete> result = creditOrderAdminService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/creditOrderAdminOrderUserNameAutoComplete")
	public Collection<CreditOrderAdminOrderUserNameAutoComplete> creditOrderAdminOrderUserNameAutoComplete(@RequestParam("query") String query) {
		Collection<CreditOrderAdminOrderUserNameAutoComplete> result = creditOrderAdminService.creditOrderAdminOrderUserNameAutoComplete(query);
		return result;
	}
	
	@GetMapping("/creditOrderAdminOrderTenantNameAutoComplete")
	public Collection<CreditOrderAdminOrderTenantNameAutoComplete> creditOrderAdminOrderTenantNameAutoComplete(@RequestParam("query") String query) {
		Collection<CreditOrderAdminOrderTenantNameAutoComplete> result = creditOrderAdminService.creditOrderAdminOrderTenantNameAutoComplete(query);
		return result;
	}
	
	@GetMapping("/creditOrderAdminSumFields")
	public CreditOrderAdminSumFields getCreditOrderAdminSumFields(CreditOrderAdminListFilter creditOrderAdminListFilter) {
		CreditOrderAdminSumFields result = creditOrderAdminService.getCreditOrderAdminSumFields(creditOrderAdminListFilter);
		return result;
	}
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/sysUserOrderUserAutoComplete")
	public Collection<SysUserAutoComplete> sysUserOrderUserAutoComplete(@RequestParam("query") String query) {
		Collection<SysUserAutoComplete> result = creditOrderAdminService.sysUserOrderUserAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
