package srdt.co.in.services;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.Permissions;
import srdt.co.in.entities.RolePermissionMapping;
import srdt.co.in.entities.Roles;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.RolePermission;
import srdt.co.in.repositories.RolePermissionRepository;

@Service
public class RolePermissionService implements RolePermissionRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public ResponceMessage saveMapping(RolePermission rolePermission) {
		// TODO Auto-generated method stub	
		em.unwrap(Session.class).setJdbcBatchSize(10);
		Roles role = em.find(Roles.class, rolePermission.getRoleid());
		if(role != null)
		{
			rolePermission.getPermissionid()
						  .stream().forEach(x->
						  {
							    Permissions permission = em.find(Permissions.class, x.longValue());
								if(permission != null)
								{
									em.persist(new RolePermissionMapping(role, permission, rolePermission.getCreatedby()));																			
								}
						  });
		}	
		em.close();
		return new ResponceMessage("Mapping success");
	}

	@Override
	@Transactional
	public ResponceMessage removeMapping(RolePermission rolePermission) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		RolePermissionMapping mapping = em.find(RolePermissionMapping.class, rolePermission.getRolepermissionmapid());
		if(mapping != null)
		{
			em.remove(mapping);
			em.close();
			return new ResponceMessage("Mapping successfully removed");
		}
		return new ResponceMessage("Mapping remove failed");
	}

	@Override
	public Roles getMappingByRoleId(long RoleId) {
		// TODO Auto-generated method stub
		Roles role = em.find(Roles.class, RoleId);		
		return role;
	}

	@Override
	@Transactional
	public ResponceMessage updateMapping(RolePermission rolePermission) {
		// TODO Auto-generated method stub
		String sql = "delete from role_permission_mapping where role_id=:roleid and permission_id not in(:permissionid)";
		em.unwrap(Session.class).setJdbcBatchSize(10);
		Query query = em.createNativeQuery(sql);
		query.setParameter("roleid", rolePermission.getRoleid());
		query.setParameter("permissionid", rolePermission.getPermissionid());
		query.executeUpdate();
		Roles role = em.find(Roles.class, rolePermission.getRoleid());
		rolePermission.getPermissionid()
		              .stream()
		              .forEach(x->
		              {
		            	  String sqlcount = "select count(*) from role_permission_mapping where role_id=:roleid and permission_id=:permissionid";
		            	  Query querycount = em.createNativeQuery(sqlcount);
		            	  querycount.setParameter("roleid", rolePermission.getRoleid());
		            	  querycount.setParameter("permissionid", x);
		            	  
		            	  BigInteger flag = (BigInteger) querycount.getResultList().get(0);
		            	  
		            	  if(flag.intValue() == 0)
		            	  {
		            		  Permissions permission = em.find(Permissions.class, x);
			            	  if(permission != null)
			            	  {
			            		  em.persist(new RolePermissionMapping(role, permission, rolePermission.getCreatedby()));
			            	  }
		            	  }           	  
		            	  
		            	  
		              });
		em.flush();
		em.clear();
		em.close();
		
		
		return new ResponceMessage("Role Mapping Updated");
	}
	
}
