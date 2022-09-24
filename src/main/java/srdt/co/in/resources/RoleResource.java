package srdt.co.in.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srdt.co.in.entities.Roles;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.Role;
import srdt.co.in.repositories.RoleRepository;

@RestController
@RequestMapping("/api/role")
public class RoleResource {

	@Autowired
	private RoleRepository service;
	
	@PostMapping("/createrole")
	public ResponceMessage createRole(@RequestBody ArrayList<Role> roles)
	{
		return service.createRole(roles);		
	}
	
	@PostMapping("/removerole")
	public ResponceMessage removeRole(@RequestBody ArrayList<Role> role)
	{
		return service.removeRole(role);
	}
	
	@GetMapping("/getroles")
	public List<Role> getRoles()
	{
		List<Role> roles = new ArrayList<Role>();
		roles.clear();
		service.getRoles()
			   .stream()
			   .forEach(x->
			   {
				   roles.add(new Role(x.getRoleId(),x.getRoleName(),x.getCreatedBy(),x.getIsActive()));
			   });
		return roles;
	}	
	
	@GetMapping("/getrolesbyloginid/{loginid}")
	public List<Role> getUserRolesByLoginId(@PathVariable("loginid") String loginid)
	{
		List<Role> roles = new ArrayList<Role>();
		roles.clear();
		service.getUserRolesByLoginId(loginid)
			   .stream()
			   .forEach(x->
			   {
				   roles.add(new Role(x.getRoleId(),x.getRoleName(),x.getCreatedBy(),x.getIsActive()));
			   });
		return roles;
	}
	
	@GetMapping("/getrolebyid/{roleid}")
	public Role getRoleById(@PathVariable("roleid") long roleid)
	{
		Roles role = service.getRoleById(roleid);
		if(role != null)
		{
			return new Role(role.getRoleId(),role.getRoleName(),role.getCreatedBy(),role.getIsActive());
		}
		return new Role();
	}
	
	@PostMapping("/search")
	public List<Role> search(@RequestBody Role role)
	{
		List<Role> roles = new ArrayList<Role>();
		roles.clear();
		service.search(role)
			   .stream()
			   .forEach(x->
			   {
				   roles.add(new Role(x.getRoleId(),x.getRoleName(),x.getCreatedBy(),x.getIsActive()));
			   });
		return roles;
	}
	
	@PostMapping("/saveas")
	public ResponceMessage saveAs(@RequestBody Role role)
	{
		return service.saveAs(role);
	}
}
