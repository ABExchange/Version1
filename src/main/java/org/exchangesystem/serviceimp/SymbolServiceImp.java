package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.SymbolDao;
import org.exchangesystem.model.Symbol;
import org.exchangesystem.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymbolServiceImp implements SymbolService {
	
	@Autowired
	SymbolDao symbolDao;

	public void add(Symbol symbol) {
		symbolDao.save(symbol);
	}

	public List<Symbol> findAll() {
		return symbolDao.findAll();
	}

	public Symbol findById(Long id) {
		return symbolDao.findOne(id);
	}

	public void remove(Symbol symbol) {
		symbolDao.delete(symbol);
	}

	public void update(Symbol symbol) {
		symbolDao.update(symbol);
	}

	public Long count() {
		return symbolDao.count();
	}

	public Symbol findSymbol(String code) {
		return symbolDao.findSymbol(code);
	}
	

}
