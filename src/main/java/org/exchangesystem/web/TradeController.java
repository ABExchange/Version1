package org.exchangesystem.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.exchangesystem.daoimp.ExchangeSystemSession;
import org.exchangesystem.model.OrderStatus;
import org.exchangesystem.model.OrderTransaction;
import org.exchangesystem.model.OrderType;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.model.TradeOrder;
import org.exchangesystem.service.OrderTransactionService;
import org.exchangesystem.service.SymbolService;
import org.exchangesystem.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TradeController {
	
	@Autowired
	SymbolService symbolService;
	@Autowired
	TradeOrderService tradeOrderService;
	
	@Autowired
	OrderTransactionService orderTransactionService;
	
	@Autowired
	ExchangeSystemSession exchangeSystemSession;
	
	protected static Logger logger = Logger.getLogger(TradeController.class);
	
	@RequestMapping(value={"/trade"}, method=RequestMethod.GET)
	public String getTrading(@ModelAttribute("tradeOrder") TradeOrder tradeOrder, Model model){
		List<Symbol> symbolList = symbolService.findAll();
		
		logger.info(" MMMMMMMMMMMMMM The number of symbols is :::::  "+symbolList.size());
		
		tradeOrder = new TradeOrder();
		model.addAttribute("tradeOrder", tradeOrder);
		model.addAttribute("symbolList", symbolList);
		
		model.addAttribute("user", exchangeSystemSession.getUser());
		
//		List<TradeOrder> listOrder = tradeOrderService.findAll(OrderStatus.OPEN);
		
		//Get all the Orders that are not closed - includes open and partial
		List<TradeOrder> listOrder = tradeOrderService.findAllUnclosed(OrderStatus.CLOSED);

		
		List<OrderTransaction> listOrderTransaction = orderTransactionService.findAll();
		model.addAttribute("listOrder", listOrder);
		model.addAttribute("listOrderTransaction", listOrderTransaction);

		
		return "trade";
	}
	
	@RequestMapping(value={"/trade/buybitcoin"}, method=RequestMethod.POST)
	public String getPostTrading(@ModelAttribute("tradeOrder") TradeOrder tradeOrder, Model model){
		
		//Save the trade order
		tradeOrder.setOrderType(OrderType.BUY);
		tradeOrder.setOrderStatus(OrderStatus.OPEN);
		tradeOrderService.add(tradeOrder);
		
		tradeOrder = new TradeOrder();
		
		List<Symbol> symbolList = symbolService.findAll();
		
		model.addAttribute("tradeOrder", tradeOrder);
		model.addAttribute("symbolList", symbolList);
		
		model.addAttribute("user", exchangeSystemSession.getUser());


		List<TradeOrder> listOrder = tradeOrderService.findAll();
		model.addAttribute("listOrder", listOrder);

		return "redirect:/trade";
	}
	
	@RequestMapping(value={"/trade/sellbitcoin"}, method=RequestMethod.POST)
	public String sellBitCoins(@ModelAttribute("tradeOrder") TradeOrder tradeOrder, Model model){
		
		tradeOrder.setOrderType(OrderType.SELL);
		tradeOrder.setOrderStatus(OrderStatus.OPEN);
		tradeOrderService.add(tradeOrder);

		tradeOrder =  new TradeOrder();
		List<Symbol> symbolList = symbolService.findAll();
		
		model.addAttribute("tradeOrder", tradeOrder);
		model.addAttribute("symbolList", symbolList);
		
		model.addAttribute("user", exchangeSystemSession.getUser());

		List<TradeOrder> listOrder = tradeOrderService.findAll();
		model.addAttribute("listOrder", listOrder);

		
		return "redirect:/trade";
	}

}
