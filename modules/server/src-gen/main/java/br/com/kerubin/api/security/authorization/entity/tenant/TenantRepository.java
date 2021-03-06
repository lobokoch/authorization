/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import org.springframework.data.repository.query.Param;

@Transactional(readOnly = true)
public interface TenantRepository extends JpaRepository<TenantEntity, java.util.UUID>, QuerydslPredicateExecutor<TenantEntity> {
	
	@Transactional
	@Modifying
	@Query("delete from TenantEntity te where te.id in ?1")
	void deleteInBulk(java.util.List<java.util.UUID> idList);
	
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.name as name from TenantEntity ac where ( upper(ac.name) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<TenantAutoComplete> autoComplete(@Param("query") String query);
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.name as name from TenantEntity ac where ( upper(ac.name) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<TenantNameAutoComplete> tenantNameAutoComplete(@Param("query") String query);
	
	// Begin generated findBy
	
	TenantEntity findByNameIgnoreCase(String nome);
	// End generated findBy
}
