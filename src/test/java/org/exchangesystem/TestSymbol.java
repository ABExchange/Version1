package org.exchangesystem;

import junit.framework.Assert;

import org.exchangesystem.model.Symbol;
import org.exchangesystem.service.SymbolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Transactional
public class TestSymbol {

	@Autowired
	SymbolService symbolService;

	@Test
	@Transactional
	public void testAddThreeSymbols() {
		Symbol symbol;

		symbol = new Symbol();
		symbol.setCode("USD");
		symbol.setDescription("US Dollar");
		symbol.setRate(0.5);
		symbol.setPrice(127.990);
		symbolService.add(symbol);

		symbol = new Symbol();
		symbol.setCode("CAD");
		symbol.setDescription("Canadian Dollar");
		symbol.setRate(0.5);
		symbol.setPrice(127.990);
		symbolService.add(symbol);

		symbol = new Symbol();
		symbol.setCode("AUD");
		symbol.setDescription("Australlian Dollar");
		symbol.setRate(0.5);
		symbol.setPrice(127.990);
		symbolService.add(symbol);

		Assert.assertTrue(symbolService.count().compareTo(new Long(3)) == 1);
	}
	
	@Test
	@Transactional
	public void testLastPrice(){
		Symbol symbol  = symbolService.findSymbol("USD");
		Assert.assertTrue(symbolService.getLastPrice(symbol) == 0.0);
		
	}
	@Test
	@Transactional
	public void testHighPrice()
	{
		Symbol symbol  = symbolService.findSymbol("USD");
		Assert.assertTrue(symbolService.getHighPrice(symbol) == 0.0);
		
	}
	
	@Test
	@Transactional
	public void testLowPrice(){
		Symbol symbol  = symbolService.findSymbol("USD");
		Assert.assertTrue(symbolService.getLowPrice(symbol) == 0.0);
	
	}
	
	@Test
	@Transactional
	public void testVolume(){
		Symbol symbol  = symbolService.findSymbol("USD");
		Assert.assertTrue(symbolService.getVolume(symbol) == 0);

	}
	
	@Test
	@Transactional
	public void testAverage(){
		Symbol symbol  = symbolService.findSymbol("USD");
		System.out.println(symbolService.getAverage(symbol));
		
		Assert.assertTrue(symbolService.getAverage(symbol) == 0.0);
		
	}

}
