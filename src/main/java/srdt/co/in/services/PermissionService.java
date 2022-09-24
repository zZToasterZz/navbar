package srdt.co.in.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.PermissionNavBarMaping;
import srdt.co.in.entities.Permissions;
import srdt.co.in.models.Permission;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.PermissionRepository;

@Service
public class PermissionService implements PermissionRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public ResponceMessage createPermission(List<Permission> permissions) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		permissions.stream()
				   .forEach(x->
				   {
					   em.persist(new Permissions(x.getPermissionname(),x.getCreatedby()));
				   });
		em.close();
		return new ResponceMessage("Permission Created");
	}

	@Override
	public ResponceMessage removePermision(List<Permission> permissions) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		permissions.stream()
				   .forEach(x->
				   {
					   Permissions permission = em.find(Permissions.class, x.getPermissionid());
					   if(permission != null)
					   {
						   em.remove(permission);
					   }
				   });
		em.close();
		return new ResponceMessage("Permission Successfully Removed");
	}

	@Override
	public List<Permissions> getPermissions() {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("AllPermissions");
		List<Permissions> permissions = extracted(query,new Permissions());
		em.close();
		return permissions;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> extracted(Query query,T t) {
		return (List<T>)query.getResultList();
	}

	@Override
	public Permissions getPermissionById(long permissionid) {
		// TODO Auto-generated method stub
		Permissions permission = em.find(Permissions.class, permissionid);
		em.close();
		return permission;
	}

	@Override
	public List<Permissions> search(Permission permission) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("SearchPermission");
		query.setParameter("Name", permission.getPermissionname());
		List<Permissions> permissions = extracted(query,new Permissions());
		em.close();
		return permissions;		
	}

	@Override
	public ResponceMessage saveAs(Permission permission) {
		// TODO Auto-generated method stub  
		
		em.unwrap(Session.class).setJdbcBatchSize(10);
		Permissions persistpermission = new Permissions(permission.getPermissionname(), permission.getCreatedby());
		em.persist(persistpermission);
		
		Query query = em.createNamedQuery("SaveAsPermission");
		query.setParameter("PermissionId", permission.getPermissionid());
		
		List<PermissionNavBarMaping> mapings = extracted(query,new PermissionNavBarMaping());
		mapings.stream()
		       .forEach(x->
		       {
		    	   em.persist(new PermissionNavBarMaping(x.getNavBars(), persistpermission,persistpermission.getCreatedBy()));
		       });
		em.close();
		
		return new ResponceMessage("Permission SaveAs");
	}

	@Override
	public List<Permissions> GetPermissionsByRoleId(Long roleid) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("GetPermissionsByRoleId");
		query.setParameter("RoleId", roleid);
		List<Permissions> permissions = extracted(query,new Permissions());
		em.close();
		return permissions;
	}
}
