/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

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
		
		tenantOpCount.setYearOp(-8263174881223374997L);
		tenantOpCount.setMonthOp(-3936925735836036900L);
		tenantOpCount.setDayOp(-3700675055671629478L);
		tenantOpCount.setHourOp(4625204085620924220L);
		tenantOpCount.setCountGet(-7814251488099362299L);
		tenantOpCount.setCountPost(-2954026158812272521L);
		tenantOpCount.setCountPut(1107011354317733556L);
		tenantOpCount.setCountDelete(-5475704027542541934L);
		tenantOpCount.setCountList(7002315578744675223L);
		tenantOpCount.setCountAutoComplete(8118351470572455278L);
		tenantOpCount.setCountOp(100942435353065861L);
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
		
		tenantOpCount.setYearOp(-5562108860410880213L);
		tenantOpCount.setMonthOp(-3088517465794323014L);
		tenantOpCount.setDayOp(1167360329049676000L);
		tenantOpCount.setHourOp(-719784249302982630L);
		tenantOpCount.setCountGet(4990186174167678186L);
		tenantOpCount.setCountPost(-8756556584413413233L);
		tenantOpCount.setCountPut(-920373639723260657L);
		tenantOpCount.setCountDelete(3953985728732523882L);
		tenantOpCount.setCountList(5226169302348575145L);
		tenantOpCount.setCountAutoComplete(8769297909736326335L);
		tenantOpCount.setCountOp(7034443587728049292L);
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
		
		tenantOpCount.setYearOp(-8396759234506963413L);
		tenantOpCount.setMonthOp(-8710384012702912961L);
		tenantOpCount.setDayOp(-7325360364665571863L);
		tenantOpCount.setHourOp(1565029803284801830L);
		tenantOpCount.setCountGet(-1168510162824906508L);
		tenantOpCount.setCountPost(-4503362297389718671L);
		tenantOpCount.setCountPut(-7987530361563707254L);
		tenantOpCount.setCountDelete(5166996788757148908L);
		tenantOpCount.setCountList(3730575026457703265L);
		tenantOpCount.setCountAutoComplete(-5976500121815958018L);
		tenantOpCount.setCountOp(-1787408258682080155L);
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
		
		tenantOpCount.setYearOp(7766654147982660831L);
		tenantOpCount.setMonthOp(-5745833767830501624L);
		tenantOpCount.setDayOp(3129052959486283024L);
		tenantOpCount.setHourOp(8934277433255454090L);
		tenantOpCount.setCountGet(8483170232793745790L);
		tenantOpCount.setCountPost(2287499189913866797L);
		tenantOpCount.setCountPut(-6590935771087780081L);
		tenantOpCount.setCountDelete(4989699551186595724L);
		tenantOpCount.setCountList(-909416009842626066L);
		tenantOpCount.setCountAutoComplete(8789187788698952253L);
		tenantOpCount.setCountOp(-1002815047242940271L);
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
		tenantOpCountEntity.setYearOp(-7334173824003881011L);
		tenantOpCountEntity.setMonthOp(6443125009834990215L);
		tenantOpCountEntity.setDayOp(-1363735422671996150L);
		tenantOpCountEntity.setHourOp(-6977670957561974115L);
		tenantOpCountEntity.setCountGet(-3846521947539364601L);
		tenantOpCountEntity.setCountPost(-8764154892695323138L);
		tenantOpCountEntity.setCountPut(2708701278325736556L);
		tenantOpCountEntity.setCountDelete(8795190666887380396L);
		tenantOpCountEntity.setCountList(6009290364242247899L);
		tenantOpCountEntity.setCountAutoComplete(3651263716108208683L);
		tenantOpCountEntity.setCountOp(-7574559313552057815L);
		
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
		tenantEntity.setMaxUsers(-7617485328650756194L);
		tenantEntity.setBalance(new java.math.BigDecimal("31103.21622"));
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
