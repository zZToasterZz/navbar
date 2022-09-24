package srdt.co.in.repositories;

import org.springframework.stereotype.Repository;

import srdt.co.in.entities.Roles;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.RolePermission;

@Repository
public interface RolePermissionRepository {

	ResponceMessage saveMapping(RolePermission rolePermission);
	ResponceMessage removeMapping(RolePermission rolePermission);
	Roles getMappingByRoleId(long RoleId);
	ResponceMessage updateMapping(RolePermission rolePermission);
}
