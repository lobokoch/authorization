package br.com.kerubin.api.security.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.kerubin.api.security.authorization.entity.sysuser.QSysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;
import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserBaseRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SysUserBaseRepository sysUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		QSysUserEntity qUser = QSysUserEntity.sysUserEntity;
		Predicate predicate = qUser.email.eq(username); 
		
		Optional<SysUserEntity> userOptional = sysUserRepository.findOne(predicate);
		SysUserEntity user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Credênciais inválidas."));
		
		return new SystemUser(user, getUserPermissions(user));
	}

	private Collection<? extends GrantedAuthority> getUserPermissions(SysUserEntity user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		
		authorities.add(new SimpleGrantedAuthority("foo"));
		authorities.add(new SimpleGrantedAuthority("baa"));
		
		return authorities;
	}
	

}
