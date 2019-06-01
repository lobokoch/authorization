package br.com.kerubin.api.security.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUserEntity;

public class SystemUser extends User {
	
	private static final long serialVersionUID = 1L;
	private SysUserEntity user;
	
	public SystemUser(SysUserEntity user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		
		this.user = user;
	}
	
	public SysUserEntity getUser() {
		return user;
	}

	

}
