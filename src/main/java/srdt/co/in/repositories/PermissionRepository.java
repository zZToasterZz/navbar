package srdt.co.in.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import srdt.co.in.entities.Permissions;
import srdt.co.in.models.Permission;
import srdt.co.in.models.ResponceMessage;

@Repository
public interface PermissionRepository {

	ResponceMessage createPermission(List<Permission> permissions);
	ResponceMessage removePermision(List<Permission> permissions);
	List<Permissions> getPermissions();
	Permissions getPermissionById(long permissionid);
	List<Permissions> search(Permission permission);
	ResponceMessage saveAs(Permission permission);
	List<Permissions> GetPermissionsByRoleId(Long roleid);
	
}
