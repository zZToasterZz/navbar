package srdt.co.in.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.NavBars;
import srdt.co.in.models.NavBar;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.NavBarRepository;

@Service
public class NavBarService implements NavBarRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public ResponceMessage createNavBar(List<NavBar> nav) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		nav.stream()
		   .forEach(x->
		   {
			   em.persist(new NavBars(x.getNavname(),x.getNavdescr(),x.getParentid(),x.getIsparent(),x.getRequestaddr(),x.getCreatedby()));
		   });
		
		em.close();
		return new ResponceMessage("NavBar Entity Created");
	}

	@Override
	@Transactional
	public ResponceMessage removeNavBarById(NavBar nav) {
		// TODO Auto-generated method stub
		NavBars tmpnav = em.find(NavBars.class, nav.getNavbarid());
		if(tmpnav != null)
		{
			em.remove(tmpnav);
		}
		em.close();
		return new ResponceMessage("Remove NavBar Entity");
	}

	@Override
	@Transactional
	public ResponceMessage updateNavBarById(NavBar nav) {
		// TODO Auto-generated method stub
		NavBars tmpnav = em.find(NavBars.class, nav.getNavbarid());
		if(tmpnav != null)
		{
			tmpnav.setNavDescr(nav.getNavdescr());
			tmpnav.setModifiedBy(nav.getCreatedby());
			em.persist(tmpnav);
			em.close();
		}
		return new ResponceMessage("Updated NavBar Entity");
	}

	@Override
	public List<NavBars> getChild(long parentid) {
		// TODO Auto-generated method stub
		
		Query query = em.createNamedQuery("GetChildByParentId");
		query.setParameter("ParentId", parentid);
		List<NavBars> bars = extracted(query);
		return bars;
	}

	@Override
	public List<NavBars> getParent(long childid) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("GetParentByChildId");
		query.setParameter("NavBarId", childid);
		List<NavBars> bars = extracted(query);
		return bars;
	}

	@Override
	public List<NavBars> getRootParent() {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("AllRootParent");
		List<NavBars> bars = extracted(query);
		return bars;
	}

	@SuppressWarnings("unchecked")
	private List<NavBars> extracted(Query query) {
		return (List<NavBars>)query.getResultList();
	}

	@Override
	public List<NavBars> getChildByLoginId(long parentid, String loginid) {
		// TODO Auto-generated method stub
		List<Long> permissionId = getPermissionIdByLoginId(loginid);
		Query query = em.createNamedQuery("GetChildByParentIdByPermissionId");
		query.setParameter("ParentId", parentid);
		query.setParameter("PermissionId", permissionId);
		List<NavBars> bars = extracted(query);
		return bars;
	}

	@Override
	public List<NavBars> getParentByLoginId(long childid, String loginid) {
		// TODO Auto-generated method stub
		List<Long> permissionId = getPermissionIdByLoginId(loginid);
		Query query = em.createNamedQuery("GetParentByChildIdByPermissionId");
		query.setParameter("NavBarId", childid);
		query.setParameter("PermissionId", permissionId);
		List<NavBars> bars = extracted(query);		
		return bars;
	}

	@Override
	public List<NavBars> getRootParentByLoginId(String loginid) {
		// TODO Auto-generated method stub
		List<Long> permissionId = getPermissionIdByLoginId(loginid);
		
		Query query = em.createNamedQuery("AllRootParentByPermissionId");
		query.setParameter("PermissionId", permissionId);
		List<NavBars> bars = extracted(query);
		return bars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getNavBarIdByLoginId(String loginid) {
		// TODO Auto-generated method stub
		List<Long> navbarids = new ArrayList<>();
		navbarids.clear();
		String sql = "select c.nav_bar_id from user_role_mapping a " 
					 +"inner join role_permission_mapping b on a.role_id=b.role_id " 
					 +"inner join permission_nav_bar_maping c on b.permission_id=c.permission_id " 
					 +"where a.login_id=:loginid";
		Query query = em.createNativeQuery(sql);
		query.setParameter("loginid", loginid);		
		List<BigInteger> raw = query.getResultList();
		raw.stream()
		   .forEach(x->
		   {
			   navbarids.add(x.longValue());
		   });
		return navbarids;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getPermissionIdByLoginId(String loginid) {
		// TODO Auto-generated method stub
		List<Long> permissions = new ArrayList<>();
		permissions.clear();
		String sql = "select distinct b.permission_id from user_role_mapping a " 
					 +"inner join role_permission_mapping b on a.role_id=b.role_id " 
					 +"inner join permission_nav_bar_maping c on b.permission_id=c.permission_id " 
					 +"where a.login_id=:loginid";
		Query query = em.createNativeQuery(sql);
		query.setParameter("loginid", loginid);		
		List<BigInteger> raw = query.getResultList();
		raw.stream()
		   .forEach(x->
		   {
			   permissions.add(x.longValue());
		   });
		return permissions;
	}

	@Override
	public List<NavBars> getNavBarByPermissionId(String permissionid) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("GetNavBarByPermissionId");
		query.setParameter("PermissionId", permissionid);
		List<NavBars> bars = extracted(query);
		em.close();
		return bars;
	}

	@Override
	public List<NavBar> getNavbarRep(List<NavBars> navs) {
		// TODO Auto-generated method stub
		List<NavBar> list = new ArrayList<NavBar>();
		list.clear();
		if(navs != null)
		{
			navs.stream()
			    .forEach(nav -> 
			    {
			    	list.add(new NavBar(nav.getNavBarId(),nav.getNavName(),nav.getNavDescr(),nav.getParentId(),nav.getIsParent(),nav.getRequestAddr()));
			    });
		}
		return list;
	}
}
