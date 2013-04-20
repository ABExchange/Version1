package org.exchangesystem.daoimp;

import org.exchangesystem.model.ExchangeUser;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExchangeSystemSession {
	private ExchangeUser user;

	public ExchangeUser getUser() {
		return user;
	}

	public void setUser(ExchangeUser user) {
		this.user = user;
	}
	
	

}
