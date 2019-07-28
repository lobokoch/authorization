package br.com.kerubin.api.security.authorization.entity.service;

import java.util.List;

import br.com.kerubin.api.security.authorization.entity.sysuser.SysUser;

public interface SecuriryService {

	List<SysUser> listActiveUsers();

}
