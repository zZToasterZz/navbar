package srdt.co.in.services;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.NavBars;
import srdt.co.in.entities.PermissionNavBarMaping;
import srdt.co.in.entities.Permissions;
import srdt.co.in.models.NavBarMap;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.PermissionNavBarMappingRepository;

@Service
public class PermissionNavBarMappingService implements PermissionNavBarMappingRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	boolean flag = true;
	boolean prtinflag = true;
	
	@Override
	@Transactional
	public ResponceMessage navBarMapping(NavBarMap navBarMap) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		
		Permissions permissions = em.find(Permissions.class, navBarMap.getPermissionid());		
		navBarMap.getNavbarid().stream()
			     .forEach(x->
			     { 		   	 		    	
			    	
			    	 if(flag)
			    	 {
			    		 StoredProcedureQuery spquery = em.createStoredProcedureQuery("GetAllParentsByChildId");
			    		 spquery.registerStoredProcedureParameter(1, long.class, ParameterMode.IN);
			    		 spquery.registerStoredProcedureParameter(2, long.class, ParameterMode.IN);
			    		 spquery.setParameter(1, x);
			    		 spquery.setParameter(2, navBarMap.getPermissionid());
			    		 boolean execflag = spquery.execute();
			    		 if(execflag)
			    		 {
			    			 List<String> parentsid = extracted(spquery);
			    			 if(parentsid != null)
			    			 {
			    				 prtinflag = false;
			    				 parentsid.stream()
			    				 		  .forEach(y->
			    				 		  {
			    				 			 NavBars navBars = em.find(NavBars.class, Long.parseLong(y));
			    				 			 em.persist(new PermissionNavBarMaping(navBars, permissions,navBarMap.getCreatedby()));
			    				 		  });
			    			 }
			    		 }
			    		
			    		 flag = false;
			    	 }
			    	 if(prtinflag)
			    	 {
			    		 return;
			    	 }
			    	 NavBars navBars = em.find(NavBars.class, x);
			    	 em.persist(new PermissionNavBarMaping(navBars, permissions,navBarMap.getCreatedby()));
			     });
		em.close();
		return new ResponceMessage("Success");
	}

	@SuppressWarnings({ "unchecked" })
	private List<String> extracted(StoredProcedureQuery spquery) {
		return (List<String>)spquery.getResultList();
	}

	@Override
	@Transactional
	public ResponceMessage unMapNavBarMapping(NavBarMap navBarMap) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		navBarMap.getNavbarid().stream()
			     .forEach(x->
			     {
			    	 PermissionNavBarMaping maping = em.find(PermissionNavBarMaping.class, x);
			    	 if(maping != null)
			    	 {
			    		 em.remove(maping);
			    	 }
			     });
		em.close();
		return new ResponceMessage("Success");
	}

	@Override
	public ResponceMessage updateNavBarMapping(NavBarMap navBarMap) {
		// TODO Auto-generated method stub
		String sql = "delete from permission_nav_bar_maping where permission_id=:permissionid and nav_bar_id not in(:navbarid)";
		
		em.unwrap(Session.class).setJdbcBatchSize(10);
		Query query = em.createNativeQuery(sql);
		query.setParameter("permissionid", navBarMap.getPermissionid());
		query.setParameter("navbarid", navBarMap.getNavbarid());
		query.executeUpdate();
		navBarMap.getNavbarid()
		         .stream()
		         .forEach(x->
		         {
		        	 String sqlcount = "select count(*) from permission_nav_bar_maping where permission_id=:permissionid and nav_bar_id navbarid";
		        	 Query qcount = em.createNativeQuery(sqlcount);
		        	 qcount.setParameter("permissionid", navBarMap.getPermissionid());
		        	 qcount.setParameter("navbarid", x);
		     		 BigInteger flag = (BigInteger) query.getResultList().get(0);
		     		 if(flag.intValue()== 0)
		     		 {
		     			 
		     		 }
		         });
		
		return null;
	}
}
