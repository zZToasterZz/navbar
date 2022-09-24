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

import srdt.co.in.entities.Permissions;
import srdt.co.in.entities.Roles;
import srdt.co.in.models.Permission;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.RolePermission;
import srdt.co.in.services.RolePermissionService;

@RestController
@RequestMapping("/api/rolepermission")
public class RolePermissionResource {

	@Autowired
	RolePermissionService service; 
	
	@PostMapping("/savemapping")
	public ResponceMessage saveMapping(@RequestBody RolePermission rolePermission)
	{
		return service.saveMapping(rolePermission);
	}
	
	@PostMapping("/removemapping")
	public ResponceMessage removeMapping(@RequestBody RolePermission rolePermission)
	{
		return service.removeMapping(rolePermission);
	}
	
	@GetMapping("/getmappingbyroleid/{roleid}")
	public List<Permission> getMappingByRoleId(@PathVariable("roleid") long roleid)
	{
		List<Permission> permissions = new ArrayList<Permission>();
		permissions.clear();
		Roles roles = service.getMappingByRoleId(roleid);
		if(roles != null)
		{
			roles.getRolePermissionMappings()
				 .stream()
				 .forEach(x->
				 {
					 Permissions prm = x.getPermissions();
					 permissions.add(new Permission(prm.getPermissionId(),prm.getPermissionName(),prm.getCreatedBy(),prm.getIsActive()));
				 });
			     
		}
		return permissions;
	}
	
	@PostMapping("/updatemapping")
	public ResponceMessage updateMapping(@RequestBody RolePermission rolePermission)
	{
		return service.updateMapping(rolePermission);
	}
}