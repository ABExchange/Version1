package org.exchangesystem;

import junit.framework.Assert;

import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TradeOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class TestTradeOrder {

	@Autowired
	SymbolService symbolService;
	@Autowired
	TradeOrderService tradeOrderService;
	
	@Test
	@Transactional
	public void testAddAnOrder(){
		Symbol symbol = addSymbol();
		TradeOrder tradeOrder =  new TradeOrder();
		tradeOrder.setSymbol(symbol);
		tradeOrder.setQuantity(10L);
		tradeOrder.setPrice(135.0);
		tradeOrder.setTotal(135.0 * 10L);
		
		tradeOrderService.add(tradeOrder);
		
		Assert.assertTrue(tradeOrderService.count() == 1);
		
		
	}
	
	@Transactional
	public Symbol addSymbol(){
		Symbol symbol = new Symbol();
		symbol.setCode("USH");
		symbol.setDescription("Uganda Shillings");
		symbol.setPrice(30D);
		symbolService.add(symbol);
		
		symbol = symbolService.findSymbol(symbol.getCode());
		return symbol;
	}
	
}
