package org.exchangesystem.daoimp;

import org.exchangesystem.dao.UserRoleDao;
import org.exchangesystem.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoJpaImp extends AbstractJpaDao<UserRole> implements
		UserRoleDao {

}
