package srdt.co.in.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srdt.co.in.entities.UserLogins;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.UserRoleMap;
import srdt.co.in.models.UserRoles;
import srdt.co.in.repositories.UserRoleMappingRepository;

@RestController
@RequestMapping("/api/rolemap")
public class UserRoleMappingResource {

	@Autowired
	UserRoleMappingRepository service;
	
	@PostMapping("/rolemapping")
	public ResponceMessage roleMapping(@RequestBody UserRoleMap roleMaps)
	{
		return service.roleMapping(roleMaps);
	}
	
	@PostMapping("/removemapping")
	public ResponceMessage removeRoles(@RequestBody UserRoleMap roleMaps)
	{
		return service.removeRoles(roleMaps);
	}
	
	@PostMapping("/getrolesbyloginid")
	public List<UserRoles> getRolesByLoginId(@RequestBody UserRoleMap roleMaps)
	{
		List<UserRoles> roles = new ArrayList<>();
		roles.clear();
		UserLogins login = service.getRolesByUserId(roleMaps);	
		if(login != null)
		{
			login.getRoleMappings().stream()
								   .forEach(x->
								   {
									   roles.add(new UserRoles(x.getRoleMapId(), login.getLoginId(), x.getCreatedBy(), x.getRoles().getRoleName()));									   
								   });
		}
		return roles;
	}
}
