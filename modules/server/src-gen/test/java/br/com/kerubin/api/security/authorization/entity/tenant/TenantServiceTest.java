/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
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

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationBaseEntityTest;


@RunWith(SpringRunner.class)
public class TenantServiceTest extends SecurityAuthorizationBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class TenantServiceTestConfig {
		
		@Bean
		public TenantListFilterPredicate tenantListFilterPredicate() {
			return new TenantListFilterPredicateImpl();
		}
		
		@Bean
		public TenantService tenantService() {
			return new TenantServiceImpl();
		}
		
		@Bean
		public TenantDTOConverter tenantDTOConverter() {
			return new TenantDTOConverter();
		}
		
	}
	
	
	@Inject
	protected TenantService tenantService;
	
	@Inject
	protected TenantDTOConverter tenantDTOConverter;
	
	@Inject
	protected TenantRepository tenantRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		Tenant tenant = new Tenant();
		
		tenant.setId(java.util.UUID.randomUUID());
		tenant.setName(generateRandomString(255));
		tenant.setMaxUsers(8397602506988433742L);
		tenant.setBalance(new java.math.BigDecimal("31343.5560"));
		tenant.setActive(false);
		TenantEntity tenantEntity = tenantService.create(tenantDTOConverter.convertDtoToEntity(tenant));
		em.flush();
		verify(publisher, times(0)).publish(any());
		Tenant actual = tenantDTOConverter.convertEntityToDto(tenantEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenant, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		Tenant tenant = new Tenant();
		
		tenant.setId(java.util.UUID.randomUUID());
		tenant.setName(generateRandomString(255));
		tenant.setMaxUsers(-7737663425683311617L);
		tenant.setBalance(new java.math.BigDecimal("23500.27956"));
		TenantEntity tenantEntity = tenantService.create(tenantDTOConverter.convertDtoToEntity(tenant));
		em.flush();
		verify(publisher, times(0)).publish(any());
		Tenant actual = tenantDTOConverter.convertEntityToDto(tenantEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenant, IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		TenantEntity expectedTenantEntity = newTenantEntity();
		java.util.UUID id = expectedTenantEntity.getId();
		Tenant expected = tenantDTOConverter.convertEntityToDto(expectedTenantEntity);
		TenantEntity readTenantEntity = tenantService.read(id);
		Tenant actual = tenantDTOConverter.convertEntityToDto(readTenantEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		TenantEntity oldTenantEntity = newTenantEntity();
		java.util.UUID id = oldTenantEntity.getId();
				
		Tenant tenant = new Tenant();
		tenant.setId(id);
		
		tenant.setName(generateRandomString(255));
		tenant.setMaxUsers(-4826067679990741261L);
		tenant.setBalance(new java.math.BigDecimal("26377.23767"));
		tenant.setActive(false);
		TenantEntity tenantEntity = tenantService.update(id, tenantDTOConverter.convertDtoToEntity(tenant));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		Tenant actual = tenantDTOConverter.convertEntityToDto(tenantEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenant, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		TenantEntity oldTenantEntity = newTenantEntity();
		java.util.UUID id = oldTenantEntity.getId();
				
		Tenant tenant = new Tenant();
		tenant.setId(id);
		
		tenant.setName(generateRandomString(255));
		tenant.setMaxUsers(840323046658128387L);
		tenant.setBalance(new java.math.BigDecimal("9116.3831"));
		TenantEntity tenantEntity = tenantService.update(id, tenantDTOConverter.convertDtoToEntity(tenant));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		Tenant actual = tenantDTOConverter.convertEntityToDto(tenantEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenant, IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		TenantEntity expected = newTenantEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(TenantEntity.class, id);
		assertThat(expected).isNotNull();
		tenantService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(TenantEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByName() {
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
		
		// Creates a list filter for entity Tenant.
		TenantListFilter listFilter = new TenantListFilter();
		
		// Extracts 7 records of TenantEntity randomly from testData.
		final int resultSize = 7;
		List<TenantEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only TenantEntity.name field and configure this list as a filter.
		List<String> nameListFilter = filterTestData.stream().map(TenantEntity::getName).collect(Collectors.toList());
		listFilter.setName(nameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<TenantEntity> page = tenantService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<Tenant> content = page.getContent().stream().map(it -> tenantDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<Tenant> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with nameListFilter elements based on name field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(Tenant::getName)
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
		
		// Creates a list filter for entity Tenant.
		TenantListFilter listFilter = new TenantListFilter();
		
		// Generates a list with only TenantEntity.name field with 1 not found data in the database and configure this list as a filter.
		List<String> nameListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setName(nameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<TenantEntity> page = tenantService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<Tenant> content = page.getContent().stream().map(it -> tenantDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<Tenant> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown name field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
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
		// Mount the autocomplete query expression and call it.
		String query = nameListFilter.get(0);
		Collection<TenantAutoComplete> result = tenantService.autoComplete(query);
		
		// Assert TenantAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(TenantAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testTenantNameAutoComplete() {
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
		// Mount the autocomplete query expression and call it.
		String query = nameListFilter.get(0);
		Collection<TenantNameAutoComplete> result = tenantService.tenantNameAutoComplete(query);
		// Assert TenantNameAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(TenantNameAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected TenantEntity newTenantEntity() {
		TenantEntity tenantEntity = new TenantEntity();
		
		tenantEntity.setName(generateRandomString(255));
		tenantEntity.setMaxUsers(-6046908828345903592L);
		tenantEntity.setBalance(new java.math.BigDecimal("11783.29779"));
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
