/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantLookupResult;
import br.com.kerubin.api.security.authorization.AccountType;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import br.com.kerubin.api.security.authorization.common.PageResult;
import java.util.Arrays;
import java.util.Collection;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationBaseEntityTest;


@RunWith(SpringRunner.class)
public class SysUserServiceTest extends SecurityAuthorizationBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class SysUserServiceTestConfig {
		
		@Bean
		public SysUserListFilterPredicate sysUserListFilterPredicate() {
			return new SysUserListFilterPredicateImpl();
		}
		
		@Bean
		public SysUserService sysUserService() {
			return new SysUserServiceImpl();
		}
		
		@Bean
		public SysUserDTOConverter sysUserDTOConverter() {
			return new SysUserDTOConverter();
		}
		
	}
	
	
	@Inject
	protected SysUserService sysUserService;
	
	@Inject
	protected SysUserDTOConverter sysUserDTOConverter;
	
	@Inject
	protected SysUserBaseRepository sysUserBaseRepository;
	
	@Inject
	protected TenantRepository tenantRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		SysUser sysUser = new SysUser();
		
		sysUser.setId(java.util.UUID.randomUUID());
		sysUser.setName(generateRandomString(255));
		sysUser.setCnpjCPF("92020472007");
		sysUser.setEmail("fortest@gmail.com");
		sysUser.setPassword("*******");
		sysUser.setConfirmPassword("*******");
		sysUser.setActive(false);
		sysUser.setAdministrator(false);
		sysUser.setSuperAdministrator(false);
		sysUser.setAccountType(AccountType.PERSONAL);
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		sysUser.setTenant(tenant);
		
		sysUser.setActivationDate(java.time.LocalDateTime.now());
		sysUser.setConfirmed(false);
		sysUser.setConfirmationDate(java.time.LocalDateTime.now());
		sysUser.setConfirmationId(generateRandomString(255));
		SysUserEntity sysUserEntity = sysUserService.create(sysUserDTOConverter.convertDtoToEntity(sysUser));
		em.flush();
		verify(publisher, times(0)).publish(any());
		SysUser actual = sysUserDTOConverter.convertEntityToDto(sysUserEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(sysUser, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(sysUser.getTenant(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		SysUser sysUser = new SysUser();
		
		sysUser.setId(java.util.UUID.randomUUID());
		sysUser.setName(generateRandomString(255));
		sysUser.setEmail("fortest@gmail.com");
		sysUser.setPassword("*******");
		sysUser.setConfirmPassword("*******");
		sysUser.setAccountType(AccountType.PERSONAL);
		SysUserEntity sysUserEntity = sysUserService.create(sysUserDTOConverter.convertDtoToEntity(sysUser));
		em.flush();
		verify(publisher, times(0)).publish(any());
		SysUser actual = sysUserDTOConverter.convertEntityToDto(sysUserEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(sysUser, IGNORED_FIELDS);
		
		assertThat(actual.getTenant()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		SysUserEntity expectedSysUserEntity = newSysUserEntity();
		java.util.UUID id = expectedSysUserEntity.getId();
		SysUser expected = sysUserDTOConverter.convertEntityToDto(expectedSysUserEntity);
		SysUserEntity readSysUserEntity = sysUserService.read(id);
		SysUser actual = sysUserDTOConverter.convertEntityToDto(readSysUserEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		SysUserEntity oldSysUserEntity = newSysUserEntity();
		java.util.UUID id = oldSysUserEntity.getId();
				
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		
		sysUser.setName(generateRandomString(255));
		sysUser.setCnpjCPF("92020472007");
		sysUser.setEmail("fortest@gmail.com");
		sysUser.setPassword("*******");
		sysUser.setConfirmPassword("*******");
		sysUser.setActive(false);
		sysUser.setAdministrator(false);
		sysUser.setSuperAdministrator(false);
		sysUser.setAccountType(AccountType.PERSONAL);
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		sysUser.setTenant(tenant);
		
		sysUser.setActivationDate(java.time.LocalDateTime.now());
		sysUser.setConfirmed(false);
		sysUser.setConfirmationDate(java.time.LocalDateTime.now());
		sysUser.setConfirmationId(generateRandomString(255));
		SysUserEntity sysUserEntity = sysUserService.update(id, sysUserDTOConverter.convertDtoToEntity(sysUser));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		SysUser actual = sysUserDTOConverter.convertEntityToDto(sysUserEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(sysUser, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(sysUser.getTenant(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		SysUserEntity oldSysUserEntity = newSysUserEntity();
		java.util.UUID id = oldSysUserEntity.getId();
				
		SysUser sysUser = new SysUser();
		sysUser.setId(id);
		
		sysUser.setName(generateRandomString(255));
		sysUser.setEmail("fortest@gmail.com");
		sysUser.setPassword("*******");
		sysUser.setConfirmPassword("*******");
		sysUser.setAccountType(AccountType.PERSONAL);
		SysUserEntity sysUserEntity = sysUserService.update(id, sysUserDTOConverter.convertDtoToEntity(sysUser));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		SysUser actual = sysUserDTOConverter.convertEntityToDto(sysUserEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(sysUser, IGNORED_FIELDS);
		
		assertThat(actual.getTenant()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		SysUserEntity expected = newSysUserEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(SysUserEntity.class, id);
		assertThat(expected).isNotNull();
		sysUserService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(SysUserEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByName() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for SysUserEntity for this test.
		List<SysUserEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newSysUserEntity());
		}
		
		// Check if 33 records of SysUserEntity was generated.
		long count = sysUserBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity SysUser.
		SysUserListFilter listFilter = new SysUserListFilter();
		
		// Extracts 7 records of SysUserEntity randomly from testData.
		final int resultSize = 7;
		List<SysUserEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only SysUserEntity.name field and configure this list as a filter.
		List<String> nameListFilter = filterTestData.stream().map(SysUserEntity::getName).collect(Collectors.toList());
		listFilter.setName(nameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<SysUserEntity> page = sysUserService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<SysUser> content = page.getContent().stream().map(it -> sysUserDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<SysUser> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with nameListFilter elements based on name field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(SysUser::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByNameWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for SysUserEntity for this test.
		List<SysUserEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newSysUserEntity());
		}
		
		// Check if 33 records of SysUserEntity was generated.
		long count = sysUserBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity SysUser.
		SysUserListFilter listFilter = new SysUserListFilter();
		
		// Generates a list with only SysUserEntity.name field with 1 not found data in the database and configure this list as a filter.
		List<String> nameListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setName(nameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<SysUserEntity> page = sysUserService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<SysUser> content = page.getContent().stream().map(it -> sysUserDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<SysUser> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown name field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for SysUserEntity for this test.
		List<SysUserEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newSysUserEntity());
		}
		
		// Check if 33 records of SysUserEntity was generated.
		long count = sysUserBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of SysUserEntity randomly from testData.
		final int resultSize = 1;
		List<SysUserEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only SysUserEntity.name field and configure this list as a filter.
		List<String> nameListFilter = filterTestData.stream().map(SysUserEntity::getName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = nameListFilter.get(0);
		Collection<SysUserAutoComplete> result = sysUserService.autoComplete(query);
		
		// Assert SysUserAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(SysUserAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testSysUserNameAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for SysUserEntity for this test.
		List<SysUserEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newSysUserEntity());
		}
		
		// Check if 33 records of SysUserEntity was generated.
		long count = sysUserBaseRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of SysUserEntity randomly from testData.
		final int resultSize = 1;
		List<SysUserEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only SysUserEntity.name field and configure this list as a filter.
		List<String> nameListFilter = filterTestData.stream().map(SysUserEntity::getName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = nameListFilter.get(0);
		Collection<SysUserNameAutoComplete> result = sysUserService.sysUserNameAutoComplete(query);
		// Assert SysUserNameAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(SysUserNameAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testSysUserTenantAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for TenantEntity for this test.
		List<TenantEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newTenantEntity());
		}
		
		// Check if 33 records of TenantEntity was generated.
		long count = tenantRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of TenantEntity randomly from testData.
		final int resultSize = 1;
		List<TenantEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only TenantEntity.name field and configure this list as a filter.
		List<String> nameListFilter = filterTestData.stream().map(TenantEntity::getName).collect(Collectors.toList());
		String query = nameListFilter.get(0);
		
		Collection<TenantAutoComplete> result = sysUserService.tenantTenantAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(TenantAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected SysUserEntity newSysUserEntity() {
		SysUserEntity sysUserEntity = new SysUserEntity();
		
		sysUserEntity.setName(generateRandomString(255));
		sysUserEntity.setCnpjCPF("92020472007");
		sysUserEntity.setEmail("fortest@gmail.com");
		sysUserEntity.setPassword("*******");
		sysUserEntity.setConfirmPassword("*******");
		sysUserEntity.setActive(false);
		sysUserEntity.setAdministrator(false);
		sysUserEntity.setSuperAdministrator(false);
		sysUserEntity.setAccountType(AccountType.PERSONAL);
		sysUserEntity.setTenant(newTenantEntity());
		sysUserEntity.setActivationDate(java.time.LocalDateTime.now());
		sysUserEntity.setConfirmed(false);
		sysUserEntity.setConfirmationDate(java.time.LocalDateTime.now());
		sysUserEntity.setConfirmationId(generateRandomString(255));
		
		sysUserEntity = em.persistAndFlush(sysUserEntity);
		return sysUserEntity;
	}
	
	
	protected SysUserLookupResult newSysUserLookupResult(SysUserEntity sysUserEntity) {
		SysUserLookupResult sysUser = new SysUserLookupResult();
		
		sysUser.setId(sysUserEntity.getId());
		sysUser.setName(sysUserEntity.getName());
		sysUser.setEmail(sysUserEntity.getEmail());
		sysUser.setAccountType(sysUserEntity.getAccountType());
		
		return sysUser;
	}
	
	
	protected TenantEntity newTenantEntity() {
		TenantEntity tenantEntity = new TenantEntity();
		
		tenantEntity.setName(generateRandomString(255));
		tenantEntity.setMaxUsers(7842672810679643546L);
		tenantEntity.setBalance(new java.math.BigDecimal("7233.22507"));
		tenantEntity.setActive(false);
		
		tenantEntity = em.persistAndFlush(tenantEntity);
		return tenantEntity;
	}
	
	
	protected TenantLookupResult newTenantLookupResult(TenantEntity tenantEntity) {
		TenantLookupResult tenant = new TenantLookupResult();
		
		tenant.setId(tenantEntity.getId());
		tenant.setName(tenantEntity.getName());
		
		return tenant;
	}
	// END TESTS DEPENDENCIES

}
