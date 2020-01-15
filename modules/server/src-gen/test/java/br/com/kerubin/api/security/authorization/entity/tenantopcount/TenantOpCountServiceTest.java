/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

import br.com.kerubin.api.security.authorization.entity.tenant.TenantEntity;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantLookupResult;
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
import java.util.Collection;
import br.com.kerubin.api.security.authorization.entity.tenant.TenantAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.security.authorization.SecurityAuthorizationBaseEntityTest;


@RunWith(SpringRunner.class)
public class TenantOpCountServiceTest extends SecurityAuthorizationBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class TenantOpCountServiceTestConfig {
		
		@Bean
		public TenantOpCountListFilterPredicate tenantOpCountListFilterPredicate() {
			return new TenantOpCountListFilterPredicateImpl();
		}
		
		@Bean
		public TenantOpCountService tenantOpCountService() {
			return new TenantOpCountServiceImpl();
		}
		
		@Bean
		public TenantOpCountDTOConverter tenantOpCountDTOConverter() {
			return new TenantOpCountDTOConverter();
		}
		
	}
	
	
	@Inject
	protected TenantOpCountService tenantOpCountService;
	
	@Inject
	protected TenantOpCountDTOConverter tenantOpCountDTOConverter;
	
	@Inject
	protected TenantOpCountRepository tenantOpCountRepository;
	
	@Inject
	protected TenantRepository tenantRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		TenantOpCount tenantOpCount = new TenantOpCount();
		
		tenantOpCount.setId(java.util.UUID.randomUUID());
		tenantOpCount.setDescription(generateRandomString(255));
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		tenantOpCount.setTenant(tenant);
		
		tenantOpCount.setYearOp(-3849256375810363525L);
		tenantOpCount.setMonthOp(7512872399754905591L);
		tenantOpCount.setDayOp(-1773693826069381785L);
		tenantOpCount.setHourOp(7978317859496898842L);
		tenantOpCount.setCountGet(-5712829435605001956L);
		tenantOpCount.setCountPost(4683081595379747774L);
		tenantOpCount.setCountPut(-7571437828149553525L);
		tenantOpCount.setCountDelete(-4536360883286608226L);
		tenantOpCount.setCountList(8446107222232519050L);
		tenantOpCount.setCountAutoComplete(-2060668178435373917L);
		tenantOpCount.setCountOp(-6861647299285448264L);
		TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.create(tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
		em.flush();
		verify(publisher, times(0)).publish(any());
		TenantOpCount actual = tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenantOpCount, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(tenantOpCount.getTenant(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		TenantOpCount tenantOpCount = new TenantOpCount();
		
		tenantOpCount.setId(java.util.UUID.randomUUID());
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		tenantOpCount.setTenant(tenant);
		
		tenantOpCount.setYearOp(-6299661226652587843L);
		tenantOpCount.setMonthOp(5041957466810882429L);
		tenantOpCount.setDayOp(2468799236401231538L);
		tenantOpCount.setHourOp(-6335065341830965663L);
		tenantOpCount.setCountGet(-2585554249499575571L);
		tenantOpCount.setCountPost(-8662693577812431743L);
		tenantOpCount.setCountPut(734325617379710978L);
		tenantOpCount.setCountDelete(-6191574704818458254L);
		tenantOpCount.setCountList(-971343829473576068L);
		tenantOpCount.setCountAutoComplete(4847787642601961908L);
		tenantOpCount.setCountOp(-3608144481116772747L);
		TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.create(tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
		em.flush();
		verify(publisher, times(0)).publish(any());
		TenantOpCount actual = tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenantOpCount, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(tenantOpCount.getTenant(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		TenantOpCountEntity expectedTenantOpCountEntity = newTenantOpCountEntity();
		java.util.UUID id = expectedTenantOpCountEntity.getId();
		TenantOpCount expected = tenantOpCountDTOConverter.convertEntityToDto(expectedTenantOpCountEntity);
		TenantOpCountEntity readTenantOpCountEntity = tenantOpCountService.read(id);
		TenantOpCount actual = tenantOpCountDTOConverter.convertEntityToDto(readTenantOpCountEntity);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		TenantOpCountEntity oldTenantOpCountEntity = newTenantOpCountEntity();
		java.util.UUID id = oldTenantOpCountEntity.getId();
				
		TenantOpCount tenantOpCount = new TenantOpCount();
		tenantOpCount.setId(id);
		
		tenantOpCount.setDescription(generateRandomString(255));
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		tenantOpCount.setTenant(tenant);
		
		tenantOpCount.setYearOp(5499349335202128985L);
		tenantOpCount.setMonthOp(-7466761174921621904L);
		tenantOpCount.setDayOp(-2664566952233955434L);
		tenantOpCount.setHourOp(-1659847191142267556L);
		tenantOpCount.setCountGet(-2585653150857587558L);
		tenantOpCount.setCountPost(-8391531409645766635L);
		tenantOpCount.setCountPut(-6600109606819691352L);
		tenantOpCount.setCountDelete(-7728199381524334765L);
		tenantOpCount.setCountList(-4481502753436138946L);
		tenantOpCount.setCountAutoComplete(-62554311496411466L);
		tenantOpCount.setCountOp(-2733633922985795695L);
		TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.update(id, tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		TenantOpCount actual = tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenantOpCount, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(tenantOpCount.getTenant(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		TenantOpCountEntity oldTenantOpCountEntity = newTenantOpCountEntity();
		java.util.UUID id = oldTenantOpCountEntity.getId();
				
		TenantOpCount tenantOpCount = new TenantOpCount();
		tenantOpCount.setId(id);
		
		
		TenantEntity tenantEntityParam = newTenantEntity();
		TenantLookupResult tenant = newTenantLookupResult(tenantEntityParam);
		tenantOpCount.setTenant(tenant);
		
		tenantOpCount.setYearOp(-9137459092101941027L);
		tenantOpCount.setMonthOp(-5079010047076919898L);
		tenantOpCount.setDayOp(1294682334626519098L);
		tenantOpCount.setHourOp(-9139852626559416276L);
		tenantOpCount.setCountGet(-7595637793732891114L);
		tenantOpCount.setCountPost(1887723966048774453L);
		tenantOpCount.setCountPut(-6996207732295092221L);
		tenantOpCount.setCountDelete(-2738649035911323242L);
		tenantOpCount.setCountList(4422326941457707667L);
		tenantOpCount.setCountAutoComplete(4613578370250222575L);
		tenantOpCount.setCountOp(6906431209264078671L);
		TenantOpCountEntity tenantOpCountEntity = tenantOpCountService.update(id, tenantOpCountDTOConverter.convertDtoToEntity(tenantOpCount));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		TenantOpCount actual = tenantOpCountDTOConverter.convertEntityToDto(tenantOpCountEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(tenantOpCount, IGNORED_FIELDS);
		
		
		assertThat(actual.getTenant().getId()).isNotNull();
		assertThat(actual.getTenant()).isEqualToIgnoringGivenFields(tenantOpCount.getTenant(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		TenantOpCountEntity expected = newTenantOpCountEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(TenantOpCountEntity.class, id);
		assertThat(expected).isNotNull();
		tenantOpCountService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(TenantOpCountEntity.class, id);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for TenantOpCountEntity for this test.
		List<TenantOpCountEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newTenantOpCountEntity());
		}
		
		// Check if 33 records of TenantOpCountEntity was generated.
		long count = tenantOpCountRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of TenantOpCountEntity randomly from testData.
		final int resultSize = 1;
		List<TenantOpCountEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only TenantOpCountEntity.description field and configure this list as a filter.
		List<String> descriptionListFilter = filterTestData.stream().map(TenantOpCountEntity::getDescription).collect(Collectors.toList());
		// Mount the autocomplete query expression and call it.
		String query = descriptionListFilter.get(0);
		Collection<TenantOpCountAutoComplete> result = tenantOpCountService.autoComplete(query);
		
		// Assert TenantOpCountAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(TenantOpCountAutoComplete::getDescription)
		.containsExactlyInAnyOrderElementsOf(descriptionListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testTenantOpCountTenantAutoComplete() {
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
		
		Collection<TenantAutoComplete> result = tenantOpCountService.tenantTenantAutoComplete(query);
		
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
	
	
	protected TenantOpCountEntity newTenantOpCountEntity() {
		TenantOpCountEntity tenantOpCountEntity = new TenantOpCountEntity();
		
		tenantOpCountEntity.setDescription(generateRandomString(255));
		tenantOpCountEntity.setTenant(newTenantEntity());
		tenantOpCountEntity.setYearOp(-2631206454091116102L);
		tenantOpCountEntity.setMonthOp(-1313009980609333205L);
		tenantOpCountEntity.setDayOp(-4624183243604529522L);
		tenantOpCountEntity.setHourOp(-8663039561070934870L);
		tenantOpCountEntity.setCountGet(-3232593907992266957L);
		tenantOpCountEntity.setCountPost(-7338753457979700852L);
		tenantOpCountEntity.setCountPut(2021357779600543965L);
		tenantOpCountEntity.setCountDelete(2220700868650355163L);
		tenantOpCountEntity.setCountList(-6436346113765830931L);
		tenantOpCountEntity.setCountAutoComplete(-6932910429723399512L);
		tenantOpCountEntity.setCountOp(-1586132551439373640L);
		
		tenantOpCountEntity = em.persistAndFlush(tenantOpCountEntity);
		return tenantOpCountEntity;
	}
	
	
	protected TenantOpCountLookupResult newTenantOpCountLookupResult(TenantOpCountEntity tenantOpCountEntity) {
		TenantOpCountLookupResult tenantOpCount = new TenantOpCountLookupResult();
		
		tenantOpCount.setId(tenantOpCountEntity.getId());
		tenantOpCount.setDescription(tenantOpCountEntity.getDescription());
		
		return tenantOpCount;
	}
	
	
	protected TenantEntity newTenantEntity() {
		TenantEntity tenantEntity = new TenantEntity();
		
		tenantEntity.setName(generateRandomString(255));
		tenantEntity.setMaxUsers(8433221780298126005L);
		tenantEntity.setBalance(new java.math.BigDecimal("11917.10509"));
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
