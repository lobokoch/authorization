package br.com.kerubin.api.user.account.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserBaseRepository;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;

@Repository
public interface UserAccountRepository extends SysUserBaseRepository {

	Optional<SysUserEntity> findByEmailIgnoreCase(String email);

	Optional<SysUserEntity> findByConfirmationId(String confirmationId);

}
