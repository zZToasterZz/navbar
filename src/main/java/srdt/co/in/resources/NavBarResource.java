package srdt.co.in.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srdt.co.in.entities.NavBars;
import srdt.co.in.models.NavBar;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.NavBarsJPARepository;
import srdt.co.in.services.NavBarService;

@RestController
@RequestMapping("/api/navbar")
public class NavBarResource {

	@Autowired
	NavBarService service;
	@Autowired
	NavBarsJPARepository nbrep;
	
	@PostMapping("/createnavbar")
	public ResponceMessage createNavBar(@RequestBody List<NavBar> nav)
	{
		return service.createNavBar(nav);
	}
	
	@PostMapping("/removenavbarbyid")
	public ResponceMessage removeNavBarById(@RequestBody NavBar nav)
	{
		return service.removeNavBarById(nav);
	}
	
	@PostMapping("/updatenavbarbyid")
	public ResponceMessage updateNavBarById(@RequestBody NavBar nav)
	{
		return service.updateNavBarById(nav);
	}
	
	@GetMapping("/getchild/{parentid}")
	public List<NavBar> getChild(@PathVariable("parentid") long parentid)
	{
		List<NavBars> navs = service.getChild(parentid);
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getroots")
	public List<NavBar> getroots()
	{
		List<NavBars> navs = service.getRootParent();
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getparent/{childid}")
	public List<NavBar> getParent(@PathVariable("childid") long childid)
	{
		List<NavBars> navs = service.getParent(childid);
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getchildbyloginid/{parentid}/{loginid}")
	public List<NavBar> getChildByLoginId(@PathVariable("parentid") long parentid,@PathVariable("loginid") String loginid)
	{
		List<NavBars> navs = service.getChildByLoginId(parentid,loginid);
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getrootsbyloginid/{loginid}")
	public List<NavBar> getRootsByLoginId(@PathVariable("loginid") String loginid)
	{
		List<NavBars> navs = service.getRootParentByLoginId(loginid);
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getparentbyloginid/{childid}/{loginid}")
	public List<NavBar> getParentByLoginId(@PathVariable("childid") long childid,@PathVariable("loginid") String loginid)
	{
		List<NavBars> navs = service.getParentByLoginId(childid,loginid);
		return service.getNavbarRep(navs);
	}
	public List<NavBar> getNavBarByPermissionId(String permissionid)
	{
		List<NavBars> navs = service.getNavBarByPermissionId(permissionid);		
		return service.getNavbarRep(navs);
	}
	
	@GetMapping("/getallparents")
	public List<NavBar> getAllParents()
	{
		return service.getNavbarRep(nbrep.AllParents());
	}
	
	@GetMapping("/getallnodes")
	public List<NavBar> getAllNodes()
	{
		return service.getNavbarRep(nbrep.getAllNodes());
	}	
}
