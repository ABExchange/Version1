package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.BankDao;
import org.exchangesystem.model.Bank;
import org.exchangesystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImp implements BankService {
	
	@Autowired
	BankDao bankDao;

	public void add(Bank bank) {
		bankDao.save(bank);
	}

	public List<Bank> findAll() {
		return bankDao.findAll();
	}

	public Bank findById(Long id) {
		return bankDao.findOne(id);
	}

	public void remove(Bank bank) {
		bankDao.delete(bank);
	}

	public void update(Bank bank) {
		// TODO Auto-generated method stub
		bankDao.update(bank);
	}

	public Long count() {
		// TODO Auto-generated method stub
		return bankDao.count();
	}

}
