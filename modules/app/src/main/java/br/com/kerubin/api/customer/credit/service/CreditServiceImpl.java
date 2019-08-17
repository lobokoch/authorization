package br.com.kerubin.api.customer.credit.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.customer.payment.exception.CustomerPaymentException;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.component.UserHelper;
import lombok.extern.slf4j.Slf4j; 

@Slf4j
@Service
public class CreditServiceImpl implements CreditService {
	
	@Inject
	private UserHelper userHelper;
	
	/*@Inject
	private TenantRepository tenantRepository;*/
	
	@Transactional
	@Override
	public BigDecimal getCreditBalance() {
		SysUserEntity user = userHelper.getContextUser();
		if (isEmpty(user)) {
			throw new CustomerPaymentException("O contexto da requisição não possui um usuário");
		}
		
		if (isEmpty(user.getTenant())) {
			log.warn("O usuário não possui tenant associado, context user e-mail: {}.", user.getEmail());
			throw new CustomerPaymentException("O usuário não possui tenant associado.");
		}
		
		if (isEmpty(user.getTenant().getName())) {
			log.warn("O usuário não possui nome do tenant associado, context user e-mail: {}.", user.getEmail());
			throw new CustomerPaymentException("O tenant do usuário é inválido.");
		}
		
		BigDecimal result = user.getTenant().getBalance();
		return result;
	}

}
