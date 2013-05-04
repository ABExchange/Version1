package org.exchangesystem.serviceimp;

import java.util.List;

import org.exchangesystem.dao.CountryDao;
import org.exchangesystem.model.Country;
import org.exchangesystem.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImp implements CountryService {

	@Autowired
	CountryDao countryDao;
	
	public void add(Country country) {
		countryDao.save(country);
	}

	public List<Country> findAll() {
		return countryDao.findAll();
	}

	public Country findById(Long id) {
		return countryDao.findOne(id);
	}

	public void remove(Country country) {
		countryDao.delete(country);
	}

	public void update(Country country) {
		countryDao.update(country);
	}
	

	public Long count() {
		// TODO Auto-generated method stub
		return countryDao.count();
	}

}
