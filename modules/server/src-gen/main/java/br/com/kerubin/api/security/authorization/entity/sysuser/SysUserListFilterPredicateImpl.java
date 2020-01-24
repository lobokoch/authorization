/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.sysuser;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class SysUserListFilterPredicateImpl implements SysUserListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(SysUserListFilter sysUserListFilter) {
		if (sysUserListFilter == null) {
			return null;
		}
		
		QSysUserEntity qEntity = QSysUserEntity.sysUserEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: name
		if (!CollectionUtils.isEmpty(sysUserListFilter.getName())) {
			BooleanExpression inExpression = qEntity.name.in(sysUserListFilter.getName());
			where.and(inExpression);
		}
		// End field: name
		
		return where;
	}

}

