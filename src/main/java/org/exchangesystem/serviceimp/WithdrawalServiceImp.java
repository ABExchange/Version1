package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.WithdrawalDao;
import org.exchangesystem.model.Withdrawal;
import org.exchangesystem.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WithdrawalServiceImp implements WithdrawalService {
	
	@Autowired
	WithdrawalDao withdrawalDao;

	public void add(Withdrawal withdrawal) {
		withdrawalDao.save(withdrawal);
	}

	public List<Withdrawal> findAll() {
		return withdrawalDao.findAll();
	}

	public Withdrawal findById(Long id) {
		return withdrawalDao.findOne(id);
	}

	public void remove(Withdrawal withdrawal) {
		withdrawalDao.delete(withdrawal);
	}

	public void update(Withdrawal withdrawal) {
		withdrawalDao.update(withdrawal);
	}

	public Long count() {
		return withdrawalDao.count();
	}

}
