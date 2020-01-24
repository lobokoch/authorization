/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.tenant;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class TenantListFilterPredicateImpl implements TenantListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(TenantListFilter tenantListFilter) {
		if (tenantListFilter == null) {
			return null;
		}
		
		QTenantEntity qEntity = QTenantEntity.tenantEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: name
		if (!CollectionUtils.isEmpty(tenantListFilter.getName())) {
			BooleanExpression inExpression = qEntity.name.in(tenantListFilter.getName());
			where.and(inExpression);
		}
		// End field: name
		
		return where;
	}

}

