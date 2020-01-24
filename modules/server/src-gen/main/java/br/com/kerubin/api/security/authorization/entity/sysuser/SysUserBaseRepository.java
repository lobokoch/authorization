/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SysUserBaseRepository extends JpaRepository<SysUserEntity, java.util.UUID>, QuerydslPredicateExecutor<SysUserEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.name as name, ac.email as email, ac.accountType as accountType from SysUserEntity ac where ( upper(ac.name) like upper(concat('%', :query, '%')) ) or ( upper(ac.email) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<SysUserAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.name as name from SysUserEntity ac where ( upper(ac.name) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<SysUserNameAutoComplete> sysUserNameAutoComplete(@Param("query") String query);
}
