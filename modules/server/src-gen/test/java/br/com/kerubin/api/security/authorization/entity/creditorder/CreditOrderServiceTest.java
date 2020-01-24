/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

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
public class CreditOrderServiceTest extends SecurityAuthorizationBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" };
	
	@TestConfiguration
	static class CreditOrderServiceTestConfig {
		
		@Bean
		public CreditOrderListFilterPredicate creditOrderListFilterPredicate() {
			return new CreditOrderListFilterPredicateImpl();
		}
		
		@Bean
		public CreditOrderService creditOrderService() {
			return new CreditOrderServiceImpl();
		}
		
		@Bean
		public CreditOrderDTOConverter creditOrderDTOConverter() {
			return new CreditOrderDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CreditOrderService creditOrderService;
	
	@Inject
	protected CreditOrderDTOConverter creditOrderDTOConverter;
	
	@Inject
	protected CreditOrderRepository creditOrderRepository;
	
	@Inject
	protected SysUserBaseRepository sysUserBaseRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		CreditOrder creditOrder = new CreditOrder();
		
		creditOrder.setId(java.util.UUID.randomUUID());
		creditOrder.setOrderUserName(generateRandomString(255));
		creditOrder.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrder.setOrderUser(orderUser);
		
		creditOrder.setOrderDate(getNextDate());
		creditOrder.setOrderValue(new java.math.BigDecimal("23449.24083"));
		creditOrder.setOrderBonusValue(new java.math.BigDecimal("27859.16018"));
		creditOrder.setOrderTotalCredits(new java.math.BigDecimal("3966.6435"));
		creditOrder.setPaymentMethod(PaymentMethod.CASH);
		creditOrder.setPaymentMethodDescription(generateRandomString(255));
		creditOrder.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrder.setOrderPaidDate(java.time.LocalDate.now().plusDays(1));
		creditOrder.setOrderCanceledDate(getNextDate());
		creditOrder.setOrderHistory(generateRandomString(255));
		CreditOrderEntity creditOrderEntity = creditOrderService.create(creditOrderDTOConverter.convertDtoToEntity(creditOrder));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CreditOrder actual = creditOrderDTOConverter.convertEntityToDto(creditOrderEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrder, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrder.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		CreditOrder creditOrder = new CreditOrder();
		
		creditOrder.setId(java.util.UUID.randomUUID());
		creditOrder.setOrderUserName(generateRandomString(255));
		creditOrder.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrder.setOrderUser(orderUser);
		
		creditOrder.setOrderDate(getNextDate());
		creditOrder.setOrderValue(new java.math.BigDecimal("19708.17561"));
		creditOrder.setOrderBonusValue(new java.math.BigDecimal("2897.23647"));
		creditOrder.setOrderTotalCredits(new java.math.BigDecimal("30589.9863"));
		creditOrder.setPaymentMethod(PaymentMethod.CASH);
		creditOrder.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		CreditOrderEntity creditOrderEntity = creditOrderService.create(creditOrderDTOConverter.convertDtoToEntity(creditOrder));
		em.flush();
		verify(publisher, times(0)).publish(any());
		CreditOrder actual = creditOrderDTOConverter.convertEntityToDto(creditOrderEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrder, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrder.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CreditOrderEntity expectedCreditOrderEntity = newCreditOrderEntity();
		java.util.UUID id = expectedCreditOrderEntity.getId();
		CreditOrder expected = creditOrderDTOConverter.convertEntityToDto(expectedCreditOrderEntity);
		CreditOrderEntity readCreditOrderEntity = creditOrderService.read(id);
		CreditOrder actual = creditOrderDTOConverter.convertEntityToDto(readCreditOrderEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CreditOrderEntity oldCreditOrderEntity = newCreditOrderEntity();
		java.util.UUID id = oldCreditOrderEntity.getId();
				
		CreditOrder creditOrder = new CreditOrder();
		creditOrder.setId(id);
		
		creditOrder.setOrderUserName(generateRandomString(255));
		creditOrder.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrder.setOrderUser(orderUser);
		
		creditOrder.setOrderDate(getNextDate());
		creditOrder.setOrderValue(new java.math.BigDecimal("6329.6068"));
		creditOrder.setOrderBonusValue(new java.math.BigDecimal("868.29971"));
		creditOrder.setOrderTotalCredits(new java.math.BigDecimal("29391.11442"));
		creditOrder.setPaymentMethod(PaymentMethod.CASH);
		creditOrder.setPaymentMethodDescription(generateRandomString(255));
		creditOrder.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrder.setOrderPaidDate(java.time.LocalDate.now().plusDays(1));
		creditOrder.setOrderCanceledDate(getNextDate());
		creditOrder.setOrderHistory(generateRandomString(255));
		CreditOrderEntity creditOrderEntity = creditOrderService.update(id, creditOrderDTOConverter.convertDtoToEntity(creditOrder));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CreditOrder actual = creditOrderDTOConverter.convertEntityToDto(creditOrderEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrder, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrder.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CreditOrderEntity oldCreditOrderEntity = newCreditOrderEntity();
		java.util.UUID id = oldCreditOrderEntity.getId();
				
		CreditOrder creditOrder = new CreditOrder();
		creditOrder.setId(id);
		
		creditOrder.setOrderUserName(generateRandomString(255));
		creditOrder.setOrderTenantName(generateRandomString(255));
		
		SysUserEntity sysUserEntityParam = newSysUserEntity();
		SysUserLookupResult orderUser = newSysUserLookupResult(sysUserEntityParam);
		creditOrder.setOrderUser(orderUser);
		
		creditOrder.setOrderDate(getNextDate());
		creditOrder.setOrderValue(new java.math.BigDecimal("15519.28315"));
		creditOrder.setOrderBonusValue(new java.math.BigDecimal("27893.12247"));
		creditOrder.setOrderTotalCredits(new java.math.BigDecimal("12188.2830"));
		creditOrder.setPaymentMethod(PaymentMethod.CASH);
		creditOrder.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		CreditOrderEntity creditOrderEntity = creditOrderService.update(id, creditOrderDTOConverter.convertDtoToEntity(creditOrder));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		CreditOrder actual = creditOrderDTOConverter.convertEntityToDto(creditOrderEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(creditOrder, IGNORED_FIELDS);
		
		
		assertThat(actual.getOrderUser().getId()).isNotNull();
		assertThat(actual.getOrderUser()).isEqualToIgnoringGivenFields(creditOrder.getOrderUser(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CreditOrderEntity expected = newCreditOrderEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CreditOrderEntity.class, id);
		assertThat(expected).isNotNull();
		creditOrderService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CreditOrderEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByOrderUserName() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for CreditOrderEntity for this test.
		List<CreditOrderEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderEntity());
		}
		
		// Check if 33 records of CreditOrderEntity was generated.
		long count = creditOrderRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrder.
		CreditOrderListFilter listFilter = new CreditOrderListFilter();
		
		// Extracts 7 records of CreditOrderEntity randomly from testData.
		final int resultSize = 7;
		List<CreditOrderEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderEntity::getOrderUserName).collect(Collectors.toList());
		listFilter.setOrderUserName(orderUserNameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<CreditOrderEntity> page = creditOrderService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<CreditOrder> content = page.getContent().stream().map(it -> creditOrderDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CreditOrder> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with orderUserNameListFilter elements based on orderUserName field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(CreditOrder::getOrderUserName)
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
					
		// Generate 33 records of data for CreditOrderEntity for this test.
		List<CreditOrderEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderEntity());
		}
		
		// Check if 33 records of CreditOrderEntity was generated.
		long count = creditOrderRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrder.
		CreditOrderListFilter listFilter = new CreditOrderListFilter();
		
		// Generates a list with only CreditOrderEntity.orderUserName field with 1 not found data in the database and configure this list as a filter.
		List<String> orderUserNameListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setOrderUserName(orderUserNameListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		// Call service list method.
		Page<CreditOrderEntity> page = creditOrderService.list(listFilter, pageable);
		
		// Converts found entities to DTOs and mount the result page.
		List<CreditOrder> content = page.getContent().stream().map(it -> creditOrderDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CreditOrder> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown orderUserName field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderEntity for this test.
		List<CreditOrderEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderEntity());
		}
		
		// Check if 33 records of CreditOrderEntity was generated.
		long count = creditOrderRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CreditOrderEntity randomly from testData.
		final int resultSize = 1;
		List<CreditOrderEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderEntity::getOrderUserName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = orderUserNameListFilter.get(0);
		Collection<CreditOrderAutoComplete> result = creditOrderService.autoComplete(query);
		
		// Assert CreditOrderAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CreditOrderAutoComplete::getOrderUserName)
		.containsExactlyInAnyOrderElementsOf(orderUserNameListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testCreditOrderOrderUserNameAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CreditOrderEntity for this test.
		List<CreditOrderEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderEntity());
		}
		
		// Check if 33 records of CreditOrderEntity was generated.
		long count = creditOrderRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CreditOrderEntity randomly from testData.
		final int resultSize = 1;
		List<CreditOrderEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CreditOrderEntity.orderUserName field and configure this list as a filter.
		List<String> orderUserNameListFilter = filterTestData.stream().map(CreditOrderEntity::getOrderUserName).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = orderUserNameListFilter.get(0);
		Collection<CreditOrderOrderUserNameAutoComplete> result = creditOrderService.creditOrderOrderUserNameAutoComplete(query);
		// Assert CreditOrderOrderUserNameAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CreditOrderOrderUserNameAutoComplete::getOrderUserName)
		.containsExactlyInAnyOrderElementsOf(orderUserNameListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testCreditOrderOrderUserAutoComplete() {
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
		
		Collection<SysUserAutoComplete> result = creditOrderService.sysUserOrderUserAutoComplete(query);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(SysUserAutoComplete::getName)
		.containsExactlyInAnyOrderElementsOf(nameListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetCreditOrderSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for CreditOrderEntity for this test.
		List<CreditOrderEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCreditOrderEntity());
		}
		
		// Check if 2 records of CreditOrderEntity was generated.
		long count = creditOrderRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CreditOrder.
		CreditOrderListFilter listFilter = new CreditOrderListFilter();
		
		// Extracts 2 records of CreditOrderEntity randomly from testData.
		final int resultSize = 2;
		List<CreditOrderEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		CreditOrderSumFields expected = new CreditOrderSumFields();
		
		BigDecimal sumOrderValue = filterTestData.stream().map(it -> it.getOrderValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderValue(sumOrderValue);
		
		BigDecimal sumOrderBonusValue = filterTestData.stream().map(it -> it.getOrderBonusValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderBonusValue(sumOrderBonusValue);
		
		BigDecimal sumOrderTotalCredits = filterTestData.stream().map(it -> it.getOrderTotalCredits()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumOrderTotalCredits(sumOrderTotalCredits);
		CreditOrderSumFields actual = creditOrderService.getCreditOrderSumFields(listFilter);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CreditOrderEntity newCreditOrderEntity() {
		CreditOrderEntity creditOrderEntity = new CreditOrderEntity();
		
		creditOrderEntity.setOrderUserName(generateRandomString(255));
		creditOrderEntity.setOrderTenantName(generateRandomString(255));
		creditOrderEntity.setOrderUser(newSysUserEntity());
		creditOrderEntity.setOrderDate(getNextDate());
		creditOrderEntity.setOrderValue(new java.math.BigDecimal("29887.4442"));
		creditOrderEntity.setOrderBonusValue(new java.math.BigDecimal("22716.7183"));
		creditOrderEntity.setOrderTotalCredits(new java.math.BigDecimal("17840.25060"));
		creditOrderEntity.setPaymentMethod(PaymentMethod.CASH);
		creditOrderEntity.setPaymentMethodDescription(generateRandomString(255));
		creditOrderEntity.setOrderStatus(OrderStatus.AWAITING_PAYMENT);
		creditOrderEntity.setOrderPaidDate(java.time.LocalDate.now().plusDays(1));
		creditOrderEntity.setOrderCanceledDate(getNextDate());
		creditOrderEntity.setOrderHistory(generateRandomString(255));
		
		creditOrderEntity = em.persistAndFlush(creditOrderEntity);
		return creditOrderEntity;
	}
	
	
	protected CreditOrderLookupResult newCreditOrderLookupResult(CreditOrderEntity creditOrderEntity) {
		CreditOrderLookupResult creditOrder = new CreditOrderLookupResult();
		
		creditOrder.setId(creditOrderEntity.getId());
		creditOrder.setOrderUserName(creditOrderEntity.getOrderUserName());
		
		return creditOrder;
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
		tenantEntity.setMaxUsers(56517836782633736L);
		tenantEntity.setBalance(new java.math.BigDecimal("6609.15630"));
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
