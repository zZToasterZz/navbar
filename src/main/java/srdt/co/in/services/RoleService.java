package srdt.co.in.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.RolePermissionMapping;
import srdt.co.in.entities.Roles;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.Role;
import srdt.co.in.repositories.RoleRepository;

@Service
public class RoleService implements RoleRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	
	@Override
	@Transactional
	public ResponceMessage createRole(List<Role> roles) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		roles.stream()
			 .forEach(mapper-> {
				 em.persist(new Roles(mapper.getRolename(),mapper.getCreatedby()));
				 });
		em.close();
		return new ResponceMessage("Roles Successfully Created");
	}

	
	@Override
	@Transactional
	public ResponceMessage removeRole(List<Role> roleid) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		roleid.stream()
			  .forEach(x->
			  {
				  Roles tmprole = em.find(Roles.class, x.getRoleId());
				  if(tmprole != null)
				  {
					  em.remove(tmprole);
				  }
			  });
		em.close();
		return new ResponceMessage("Roles Successfully Removed");
	}

	@Override
	public List<Roles> getRoles() {
		// TODO Auto-generated method stub		
		Query query = em.createNamedQuery("AllRoles",Roles.class);		
		List<Roles> roles = extracted(query,new Roles());	
		em.close();
		return roles;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> extracted(Query query,T t) {
		return (List<T>)query.getResultList();
	}

	@Override
	public Roles getRoleById(long roleid) {
		// TODO Auto-generated method stub
		Roles role = em.find(Roles.class, roleid);
		em.close();
		return role;
	}

	@Override
	public List<Roles> search(Role role) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("SearchRoles",Roles.class);
		query.setParameter("RoleName", role.getRolename());
		List<Roles> roles = extracted(query,new Roles());	
		em.close();
		return roles;
	}


	@Override
	@Transactional
	public ResponceMessage saveAs(Role role) {
		// TODO Auto-generated method stub 
		em.unwrap(Session.class).setJdbcBatchSize(10);
		Roles persistrole = new Roles(role.getRolename(), role.getCreatedby());
		em.persist(persistrole);
		
		Query query = em.createNamedQuery("SaveAsRole");
		query.setParameter("RoleId", role.getRoleId());
		List<RolePermissionMapping> mappings = extracted(query,new RolePermissionMapping());
		mappings.stream()
		        .forEach(x->
		        {		        	
		        	em.persist(new RolePermissionMapping(persistrole, x.getPermissions(), role.getCreatedby()));
		        });
		em.close();
		return new ResponceMessage("Role Succssfully SaveAs");
	}


	@Override
	public List<Roles> getUserRolesByLoginId(String loginid) {
		// TODO Auto-generated method stub GetRolesByLoginId
		Query query = em.createNamedQuery("GetRolesByLoginId",Roles.class);	
		query.setParameter("LoginId", loginid);
		List<Roles> roles = extracted(query,new Roles());	
		em.close();
		return roles;
		
	}
}
