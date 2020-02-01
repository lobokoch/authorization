/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserLookupResult;
import br.com.kerubin.api.security.authorization.PaymentMethod;
import br.com.kerubin.api.security.authorization.OrderStatus;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserBaseRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantLookupResult;
import br.com.kerubin.api.security.authorization.AccountType;
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
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserAutoComplete;
import java.math.BigDecimal;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationBaseEntityTest;


@RunWith(SpringRunner.class)
public class CreditOrderAdminServiceTest extends SecurityAuthorizationBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class CreditOrderAdminServiceTestConfig {
		
		@Bean
		public CreditOrderAdminListFilterPredicate creditOrderAdminListFilterPredicate() {
			return new CreditOrderAdminListFilterPredicateImpl();
		}
		
		@Bean
		public CreditOrderAdminService creditOrderAdminService() {
			return new CreditOrderAdminServiceImpl();
		}
		
		@Bean
		public CreditOrderAdminDTOConverter creditOrderAdminDTOConverter() {
			return new CreditOrderAdminDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CreditOrderAdminService creditOrderAdminService;
	
	@Inject
	protected CreditOrderAdminDTOConverter creditOrderAdminDTOConverter;
	
	@Inject
	protected CreditOrderAdminRepository creditOrderAdminRepository;
	
	@Inject
	protected SysUserBaseRepository sysUserBaseRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		CreditOrderAdmin creditOrderAdmin = new CreditOrderAdmin();
		
		creditOrderAdmin.setId(java.util.UUID.randomUUID());
		creditOrderAdmin.setOrderUserName(generateRandomString(255));
		creditOrderAdmin.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrderAdmin.setOrderUser(orderUser);
		
		creditOrderAdmin.setOrderDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderValue(new java.math.BigDecimal("32111.27173"));
		creditOrderAdmin.setOrderBonusValue(new java.math.BigDecimal("30510.24845"));
		creditOrderAdmin.setOrderTotalCredits(new java.math.BigDecimal("7408.13012"));
		creditOrderAdmin.setPaymentMethod(PaymentMethod.CASH);
		creditOrderAdmin.setPaymentMethodDescription(generateRandomString(255));
		creditOrderAdmin.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrderAdmin.setOrderPaidDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderCanceledDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderHistory(generateRandomString(255));
		CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.create(creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CreditOrderAdmin actual = creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrderAdmin, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrderAdmin.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		CreditOrderAdmin creditOrderAdmin = new CreditOrderAdmin();
		
		creditOrderAdmin.setId(java.util.UUID.randomUUID());
		creditOrderAdmin.setOrderUserName(generateRandomString(255));
		creditOrderAdmin.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrderAdmin.setOrderUser(orderUser);
		
		creditOrderAdmin.setOrderDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderValue(new java.math.BigDecimal("685.24784"));
		creditOrderAdmin.setOrderBonusValue(new java.math.BigDecimal("1280.6177"));
		creditOrderAdmin.setOrderTotalCredits(new java.math.BigDecimal("4135.7056"));
		creditOrderAdmin.setPaymentMethod(PaymentMethod.CASH);
		creditOrderAdmin.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.create(creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CreditOrderAdmin actual = creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrderAdmin, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrderAdmin.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CreditOrderAdminEntity expectedCreditOrderAdminEntity = newCreditOrderAdminEntity();
		java.util.UUID id = expectedCreditOrderAdminEntity.getId();
		CreditOrderAdmin expected = creditOrderAdminDTOConverter.convertEntityToDto(expectedCreditOrderAdminEntity);
		CreditOrderAdminEntity readCreditOrderAdminEntity = creditOrderAdminService.read(id);
		CreditOrderAdmin actual = creditOrderAdminDTOConverter.convertEntityToDto(readCreditOrderAdminEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CreditOrderAdminEntity oldCreditOrderAdminEntity = newCreditOrderAdminEntity();
		java.util.UUID id = oldCreditOrderAdminEntity.getId();
				
		CreditOrderAdmin creditOrderAdmin = new CreditOrderAdmin();
		creditOrderAdmin.setId(id);
		
		creditOrderAdmin.setOrderUserName(generateRandomString(255));
		creditOrderAdmin.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrderAdmin.setOrderUser(orderUser);
		
		creditOrderAdmin.setOrderDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderValue(new java.math.BigDecimal("8423.19084"));
		creditOrderAdmin.setOrderBonusValue(new java.math.BigDecimal("17736.27258"));
		creditOrderAdmin.setOrderTotalCredits(new java.math.BigDecimal("10500.16039"));
		creditOrderAdmin.setPaymentMethod(PaymentMethod.CASH);
		creditOrderAdmin.setPaymentMethodDescription(generateRandomString(255));
		creditOrderAdmin.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrderAdmin.setOrderPaidDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderCanceledDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderHistory(generateRandomString(255));
		CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.update(id, creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CreditOrderAdmin actual = creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrderAdmin, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrderAdmin.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CreditOrderAdminEntity oldCreditOrderAdminEntity = newCreditOrderAdminEntity();
		java.util.UUID id = oldCreditOrderAdminEntity.getId();
				
		CreditOrderAdmin creditOrderAdmin = new CreditOrderAdmin();
		creditOrderAdmin.setId(id);
		
		creditOrderAdmin.setOrderUserName(generateRandomString(255));
		creditOrderAdmin.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrderAdmin.setOrderUser(orderUser);
		
		creditOrderAdmin.setOrderDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdmin.setOrderValue(new java.math.BigDecimal("15266.31935"));
		creditOrderAdmin.setOrderBonusValue(new java.math.BigDecimal("32324.12824"));
		creditOrderAdmin.setOrderTotalCredits(new java.math.BigDecimal("16764.2619"));
		creditOrderAdmin.setPaymentMethod(PaymentMethod.CASH);
		creditOrderAdmin.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		CreditOrderAdminEntity creditOrderAdminEntity = creditOrderAdminService.update(id, creditOrderAdminDTOConverter.convertDtoToEntity(creditOrderAdmin));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CreditOrderAdmin actual = creditOrderAdminDTOConverter.convertEntityToDto(creditOrderAdminEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrderAdmin, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrderAdmin.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CreditOrderAdminEntity expected = newCreditOrderAdminEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CreditOrderAdminEntity.class, id);
		assertThat(expected).isNotNull();
		creditOrderAdminService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CreditOrderAdminEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByOrderUserName() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 33 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrderAdmin.
		CreditOrderAdminListFilter listFilter = new CreditOrderAdminListFilter();
		
		// Extracts 7 records of CreditOrderAdminEntity randomly from testData.
		final int resultSize = 7;
		List<CreditOrderAdminEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderAdminEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderAdminEntity::getOrderUserName).collect(Collectors.toList());
		listFilter.setOrderUserName(orderUserNameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<CreditOrderAdminEntity> page = creditOrderAdminService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<CreditOrderAdmin> content = page.getContent().stream().map(it -> creditOrderAdminDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CreditOrderAdmin> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with orderUserNameListFilter elements based on orderUserName field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(CreditOrderAdmin::getOrderUserName)
		.containsExactlyInAnyOrderElementsOf(orderUserNameListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByOrderUserNameWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 33 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrderAdmin.
		CreditOrderAdminListFilter listFilter = new CreditOrderAdminListFilter();
		
		// Generates a list with only CreditOrderAdminEntity.orderUserName field with 1 not found data in the database and configure this list as a filter.
		List<String> orderUserNameListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setOrderUserName(orderUserNameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<CreditOrderAdminEntity> page = creditOrderAdminService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<CreditOrderAdmin> content = page.getContent().stream().map(it -> creditOrderAdminDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CreditOrderAdmin> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown orderUserName field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 33 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CreditOrderAdminEntity randomly from testData.
		final int resultSize = 1;
		List<CreditOrderAdminEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderAdminEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderAdminEntity::getOrderUserName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = orderUserNameListFilter.get(0);
		Collection<CreditOrderAdminAutoComplete> result = creditOrderAdminService.autoComplete(query);
		
		// Assert CreditOrderAdminAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CreditOrderAdminAutoComplete::getOrderUserName)
		.containsExactlyInAnyOrderElementsOf(orderUserNameListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testCreditOrderAdminOrderUserNameAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 33 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CreditOrderAdminEntity randomly from testData.
		final int resultSize = 1;
		List<CreditOrderAdminEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderAdminEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderAdminEntity::getOrderUserName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = orderUserNameListFilter.get(0);
		Collection<CreditOrderAdminOrderUserNameAutoComplete> result = creditOrderAdminService.creditOrderAdminOrderUserNameAutoComplete(query);
		// Assert CreditOrderAdminOrderUserNameAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CreditOrderAdminOrderUserNameAutoComplete::getOrderUserName)
		.containsExactlyInAnyOrderElementsOf(orderUserNameListFilter);
	}
	
	
	@Test
	public void testCreditOrderAdminOrderTenantNameAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 33 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CreditOrderAdminEntity randomly from testData.
		final int resultSize = 1;
		List<CreditOrderAdminEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderAdminEntity.orderTenantName field and configure this list as a filter.
		List<String> orderTenantNameListFilter = filterTestData.stream().map(CreditOrderAdminEntity::getOrderTenantName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = orderTenantNameListFilter.get(0);
		Collection<CreditOrderAdminOrderTenantNameAutoComplete> result = creditOrderAdminService.creditOrderAdminOrderTenantNameAutoComplete(query);
		// Assert CreditOrderAdminOrderTenantNameAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CreditOrderAdminOrderTenantNameAutoComplete::getOrderTenantName)
		.containsExactlyInAnyOrderElementsOf(orderTenantNameListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testCreditOrderAdminOrderUserAutoComplete() {
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
		String query = nameListFilter.get(0);
		
		Collection<SysUserAutoComplete> result = creditOrderAdminService.sysUserOrderUserAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(SysUserAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetCreditOrderAdminSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for CreditOrderAdminEntity for this test.
		List<CreditOrderAdminEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderAdminEntity());
		}
		
		// Check if 2 records of CreditOrderAdminEntity was generated.
		long count = creditOrderAdminRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrderAdmin.
		CreditOrderAdminListFilter listFilter = new CreditOrderAdminListFilter();
		
		// Extracts 2 records of CreditOrderAdminEntity randomly from testData.
		final int resultSize = 2;
		List<CreditOrderAdminEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		CreditOrderAdminSumFields expected = new CreditOrderAdminSumFields();
		
		BigDecimal sumOrderValue = filterTestData.stream().map(it -> it.getOrderValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderValue(sumOrderValue);
		
		BigDecimal sumOrderBonusValue = filterTestData.stream().map(it -> it.getOrderBonusValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderBonusValue(sumOrderBonusValue);
		
		BigDecimal sumOrderTotalCredits = filterTestData.stream().map(it -> it.getOrderTotalCredits()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderTotalCredits(sumOrderTotalCredits);
		CreditOrderAdminSumFields actual = creditOrderAdminService.getCreditOrderAdminSumFields(listFilter);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CreditOrderAdminEntity newCreditOrderAdminEntity() {
		CreditOrderAdminEntity creditOrderAdminEntity = new CreditOrderAdminEntity();
		
		creditOrderAdminEntity.setOrderUserName(generateRandomString(255));
		creditOrderAdminEntity.setOrderTenantName(generateRandomString(255));
		creditOrderAdminEntity.setOrderUser(newSysUserEntity());
		creditOrderAdminEntity.setOrderDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdminEntity.setOrderValue(new java.math.BigDecimal("15916.28274"));
		creditOrderAdminEntity.setOrderBonusValue(new java.math.BigDecimal("1658.12430"));
		creditOrderAdminEntity.setOrderTotalCredits(new java.math.BigDecimal("13527.24514"));
		creditOrderAdminEntity.setPaymentMethod(PaymentMethod.CASH);
		creditOrderAdminEntity.setPaymentMethodDescription(generateRandomString(255));
		creditOrderAdminEntity.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrderAdminEntity.setOrderPaidDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdminEntity.setOrderCanceledDate(java.time.LocalDate.now().minusDays(1));
		creditOrderAdminEntity.setOrderHistory(generateRandomString(255));
		
		creditOrderAdminEntity = em.persistAndFlush(creditOrderAdminEntity);
		return creditOrderAdminEntity;
	}
	
	
	protected CreditOrderAdminLookupResult newCreditOrderAdminLookupResult(CreditOrderAdminEntity creditOrderAdminEntity) {
		CreditOrderAdminLookupResult creditOrderAdmin = new CreditOrderAdminLookupResult();
		
		creditOrderAdmin.setId(creditOrderAdminEntity.getId());
		creditOrderAdmin.setOrderUserName(creditOrderAdminEntity.getOrderUserName());
		
		return creditOrderAdmin;
	}
	
	
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
		tenantEntity.setMaxUsers(-3185212622069159764L);
		tenantEntity.setBalance(new java.math.BigDecimal("11748.1548"));
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
