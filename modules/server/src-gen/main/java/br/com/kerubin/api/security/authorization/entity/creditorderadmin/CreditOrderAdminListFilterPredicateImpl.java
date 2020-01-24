/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorderadmin;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class CreditOrderAdminListFilterPredicateImpl implements CreditOrderAdminListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(CreditOrderAdminListFilter creditOrderAdminListFilter) {
		if (creditOrderAdminListFilter == null) {
			return null;
		}
		
		QCreditOrderAdminEntity qEntity = QCreditOrderAdminEntity.creditOrderAdminEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: id
		if (creditOrderAdminListFilter.getId() != null) {
			BooleanExpression idIsEqualTo = qEntity.id.eq(creditOrderAdminListFilter.getId());
			where.and(idIsEqualTo);
		}
		// End field: id
		
		// Begin field: orderUserName
		if (!CollectionUtils.isEmpty(creditOrderAdminListFilter.getOrderUserName())) {
			BooleanExpression inExpression = qEntity.orderUserName.in(creditOrderAdminListFilter.getOrderUserName());
			where.and(inExpression);
		}
		// End field: orderUserName
		
		// Begin field: orderTenantName
		if (!CollectionUtils.isEmpty(creditOrderAdminListFilter.getOrderTenantName())) {
			BooleanExpression inExpression = qEntity.orderTenantName.in(creditOrderAdminListFilter.getOrderTenantName());
			where.and(inExpression);
		}
		// End field: orderTenantName
		
		
		// Begin field: OrderDate
		java.time.LocalDate fieldFromOrderDate = creditOrderAdminListFilter.getOrderDateFrom();
		java.time.LocalDate fieldToOrderDate = creditOrderAdminListFilter.getOrderDateTo();
		
		if (fieldFromOrderDate != null && fieldToOrderDate != null) {
			if (fieldFromOrderDate.isAfter(fieldToOrderDate)) {
				throw new IllegalArgumentException("Valor de \"Pedidos de\" não pode ser maior do que valor de \"até\".");
			}
			
			BooleanExpression between = qEntity.orderDate.between(fieldFromOrderDate, fieldToOrderDate);
			where.and(between);
		}
		else {
			if (fieldFromOrderDate != null) {
				where.and(qEntity.orderDate.goe(fieldFromOrderDate));
			}
			else if (fieldToOrderDate != null) {
				where.and(qEntity.orderDate.loe(fieldToOrderDate));				
			}
		}
		// End field: OrderDate
		
		
		// Begin field: OrderValue
		java.math.BigDecimal fieldFromOrderValue = creditOrderAdminListFilter.getOrderValueFrom();
		java.math.BigDecimal fieldToOrderValue = creditOrderAdminListFilter.getOrderValueTo();
		
		if (fieldFromOrderValue != null && fieldToOrderValue != null) {
			
			BooleanExpression between = qEntity.orderValue.between(fieldFromOrderValue, fieldToOrderValue);
			where.and(between);
		}
		else {
			if (fieldFromOrderValue != null) {
				where.and(qEntity.orderValue.goe(fieldFromOrderValue));
			}
			else if (fieldToOrderValue != null) {
				where.and(qEntity.orderValue.loe(fieldToOrderValue));				
			}
		}
		// End field: OrderValue
		
		// Begin field: orderStatus
		if (creditOrderAdminListFilter.getOrderStatus() != null) {
			BooleanExpression orderStatusIsEqualTo = qEntity.orderStatus.eq(creditOrderAdminListFilter.getOrderStatus());
			where.and(orderStatusIsEqualTo);
		}
		// End field: orderStatus
		
		return where;
	}

}

