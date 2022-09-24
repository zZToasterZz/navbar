package srdt.co.in.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import srdt.co.in.entities.NavBars;
import srdt.co.in.models.NavBar;
import srdt.co.in.models.ResponceMessage;

@Repository
public interface NavBarRepository {

	ResponceMessage createNavBar(List<NavBar> nav);
	ResponceMessage removeNavBarById(NavBar nav);
	ResponceMessage updateNavBarById(NavBar nav);
	List<NavBars> getChild(long parentid);
	List<NavBars> getParent(long childid);
	List<NavBars> getRootParent();
	
	List<NavBars> getChildByLoginId(long parentid,String loginid);
	List<NavBars> getParentByLoginId(long childid,String loginid);
	List<NavBars> getRootParentByLoginId(String loginid);
	List<Long> getNavBarIdByLoginId(String loginid);
	List<Long> getPermissionIdByLoginId(String loginid);
	List<NavBars> getNavBarByPermissionId(String permissionid);
	
	List<NavBar> getNavbarRep(List<NavBars> navs);
	
}
