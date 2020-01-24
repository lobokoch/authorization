/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenantopcount;


import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

@Component
public class TenantOpCountListFilterPredicateImpl implements TenantOpCountListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(TenantOpCountListFilter tenantOpCountListFilter) {
		if (tenantOpCountListFilter == null) {
			return null;
		}
		
		BooleanBuilder where = new BooleanBuilder();
		
		
		return where;
	}

}

