package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.TransferMethodDao;
import org.exchangesystem.model.TransferMethod;
import org.exchangesystem.service.TransferMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferMethodServiceImp implements TransferMethodService {

	@Autowired
	TransferMethodDao transferMethodDao;
	
	public void add(TransferMethod transferMethod) {
		transferMethodDao.save(transferMethod);
	}

	public List<TransferMethod> findAll() {
		return transferMethodDao.findAll();
	}

	public TransferMethod findById(Long id) {
		return transferMethodDao.findOne(id);
	}

	public void remove(TransferMethod transferMethod) {
		transferMethodDao.delete(transferMethod);
	}

	public void update(TransferMethod transferMethod) {
		transferMethodDao.update(transferMethod);
	}

	public Long count() {
		return transferMethodDao.count();
	}

}
