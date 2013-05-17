package org.exchangesystem.daoimp;

import org.exchangesystem.model.ExchangeUser;
import org.exchangesystem.model.TradeStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ExchangeSystemSession {
	private ExchangeUser user;
	private TradeStatus tradeStatus;

	public TradeStatus getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(TradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public ExchangeUser getUser() {
		return user;
	}

	public void setUser(ExchangeUser user) {
		this.user = user;
	}
	
	

}
