package srdt.co.in.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.Roles;
import srdt.co.in.entities.UserLogins;
import srdt.co.in.entities.UserRoleMappings;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.UserRoleMap;
import srdt.co.in.repositories.UserRoleMappingRepository;

@Service
public class UserRoleMappingService implements UserRoleMappingRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	@Override
	@Transactional
	public ResponceMessage roleMapping(UserRoleMap roleMaps) {
		// TODO Auto-generated method stub
		
		em.unwrap(Session.class).setJdbcBatchSize(10);
		UserLogins login = em.find(UserLogins.class, roleMaps.getUserid());
		if(login != null)
		{
			roleMaps.getActionids().stream()
			        .forEach(x->
			        {
			        	Roles role = em.find(Roles.class, x.longValue());
			        	if(role != null)
			        	{
			        		em.persist(new UserRoleMappings(login, role, roleMaps.getCreatedby()));
			        	}
			        });
			em.close();
			return new ResponceMessage("Success");
		}
		em.close();
		return new ResponceMessage("User Not Found");
	}

	@Override
	@Transactional
	public ResponceMessage removeRoles(UserRoleMap roleMaps) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		roleMaps.getActionids()
				.stream()
				.forEach(x->
				{
					UserRoleMappings mapping = em.find(UserRoleMappings.class, x.longValue());
					if(mapping != null)
					{
						em.remove(mapping);
					}
				});
		em.close();
		return new ResponceMessage("Removed");
	}

	@Override
	public UserLogins getRolesByUserId(UserRoleMap roleMaps) {
		// TODO Auto-generated method stub
		UserLogins login = em.find(UserLogins.class , roleMaps.getUserid());
		em.close();
		return login;
	}

}
