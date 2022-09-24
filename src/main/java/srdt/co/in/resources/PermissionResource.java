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
import srdt.co.in.models.Permission;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.PermissionRepository;

@RestController
@RequestMapping("/api/permission")
public class PermissionResource {
	
	@Autowired
	PermissionRepository service;
	
	@PostMapping("/createpermission")
	public ResponceMessage createPermission(@RequestBody ArrayList<Permission> permissions)
	{
		return service.createPermission(permissions);
	}
	
	@PostMapping("/removepermission")
	public ResponceMessage removePermission(@RequestBody ArrayList<Permission> permissions)
	{
		return service.removePermision(permissions);
	}
	
	@GetMapping("/getpermissions")
	public List<Permission> getPermissions()
	{
		List<Permission> permissions = new ArrayList<>();
		permissions.clear();
	 	service.getPermissions()
	 		   .stream()
	 		   .forEach(x->
	 		   {
	 			  permissions.add(new Permission(x.getPermissionId(),x.getPermissionName(),x.getCreatedBy(),x.getIsActive()));
	 		   });
		return permissions;
	}
	
	@GetMapping("/getpermissionbyid/{permissionid}")
	public Permission getPermissionById(@PathVariable("permissionid") long permissionid)
	{
		Permissions permission = service.getPermissionById(permissionid);
		if(permission != null)
		{
			return new Permission(permission.getPermissionId(),permission.getPermissionName(),permission.getCreatedBy(),permission.getIsActive());
		}
		return null;
	}
	
	@GetMapping("/search")
	public List<Permission> getPermissions(@RequestBody Permission permission)
	{
		List<Permission> permissions = new ArrayList<>();
		permissions.clear();
	 	service.search(permission)
	 		   .stream()
	 		   .forEach(x->
	 		   {
	 			  permissions.add(new Permission(x.getPermissionId(),x.getPermissionName(),x.getCreatedBy(),x.getIsActive()));
	 		   });
		return permissions;
	}
	
	public ResponceMessage saveAs(Permission permission)
	{
		return service.saveAs(permission);
	}
	
	@GetMapping("/getpermissionsbyroleid/{roleid}")
	public List<Permission> GetPermissionsByRoleId(@PathVariable("roleid") Long roleid)
	{
		List<Permission> permissions = new ArrayList<>();
		permissions.clear();
	 	service.GetPermissionsByRoleId(roleid)
	 		   .stream()
	 		   .forEach(x->
	 		   {
	 			  permissions.add(new Permission(x.getPermissionId(),x.getPermissionName(),x.getCreatedBy(),x.getIsActive()));
	 		   });
		return permissions;
	}
}
