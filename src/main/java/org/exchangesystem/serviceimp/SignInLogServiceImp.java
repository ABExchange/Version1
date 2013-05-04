package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.SignInLogDao;
import org.exchangesystem.model.SignInLog;
import org.exchangesystem.service.SignInLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInLogServiceImp implements SignInLogService {

	@Autowired
	SignInLogDao signInLogDao;
	public void add(SignInLog entity) {
		signInLogDao.save(entity);
	}

	public List<SignInLog> findAll() {
		return signInLogDao.findAll();
	}

	public SignInLog findById(Long id) {
		return signInLogDao.findOne(id);
	}

	public void remove(SignInLog entity) {
		signInLogDao.delete(entity);
	}

	public void update(SignInLog entity) {
		signInLogDao.update(entity);
	}

	public Long count() {
		return signInLogDao.count();
	}
}
