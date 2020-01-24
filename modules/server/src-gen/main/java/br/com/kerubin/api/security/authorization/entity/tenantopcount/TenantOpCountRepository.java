/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface TenantOpCountRepository extends JpaRepository<TenantOpCountEntity, java.util.UUID>, QuerydslPredicateExecutor<TenantOpCountEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.description as description from TenantOpCountEntity ac where ( upper(ac.description) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<TenantOpCountAutoComplete> autoComplete(@Param("query") String query);
}
