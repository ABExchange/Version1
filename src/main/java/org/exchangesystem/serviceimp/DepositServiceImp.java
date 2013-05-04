package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.DepositDao;
import org.exchangesystem.model.Deposit;
import org.exchangesystem.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositServiceImp implements DepositService {
	
	@Autowired
	DepositDao depositDao;

	public void add(Deposit deposit) {
		depositDao.save(deposit);
	}

	public List<Deposit> findAll() {
		return depositDao.findAll();
	}

	public Deposit findById(Long id) {
		return depositDao.findOne(id);
	}

	public void remove(Deposit deposit) {
		depositDao.delete(deposit);
	}

	public void update(Deposit deposit) {
		depositDao.update(deposit);
	}

	public Long count() {
		return depositDao.count();
	}

}
