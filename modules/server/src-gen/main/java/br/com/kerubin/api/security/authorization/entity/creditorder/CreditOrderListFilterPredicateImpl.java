/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:05:57.276
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.security.authorization.entity.creditorder;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class CreditOrderListFilterPredicateImpl implements CreditOrderListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(CreditOrderListFilter creditOrderListFilter) {
		if (creditOrderListFilter == null) {
			return null;
		}
		
		QCreditOrderEntity qEntity = QCreditOrderEntity.creditOrderEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: id
		if (creditOrderListFilter.getId() != null) {
			BooleanExpression idIsEqualTo = qEntity.id.eq(creditOrderListFilter.getId());
			where.and(idIsEqualTo);
		}
		// End field: id
		
		// Begin field: orderUserName
		if (!CollectionUtils.isEmpty(creditOrderListFilter.getOrderUserName())) {
			BooleanExpression inExpression = qEntity.orderUserName.in(creditOrderListFilter.getOrderUserName());
			where.and(inExpression);
		}
		// End field: orderUserName
		
		
		// Begin field: OrderDate
		java.time.LocalDate fieldFromOrderDate = creditOrderListFilter.getOrderDateFrom();
		java.time.LocalDate fieldToOrderDate = creditOrderListFilter.getOrderDateTo();
		
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
		java.math.BigDecimal fieldFromOrderValue = creditOrderListFilter.getOrderValueFrom();
		java.math.BigDecimal fieldToOrderValue = creditOrderListFilter.getOrderValueTo();
		
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
		if (creditOrderListFilter.getOrderStatus() != null) {
			BooleanExpression orderStatusIsEqualTo = qEntity.orderStatus.eq(creditOrderListFilter.getOrderStatus());
			where.and(orderStatusIsEqualTo);
		}
		// End field: orderStatus
		
		return where;
	}

}
