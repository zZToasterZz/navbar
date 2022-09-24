package srdt.co.in.repositories;

import org.springframework.stereotype.Repository;

import srdt.co.in.entities.UserLogins;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.UserRoleMap;

@Repository
public interface UserRoleMapingRepository {

	ResponceMessage roleMapping(UserRoleMap roleMaps);
	ResponceMessage removeRoles(UserRoleMap roleMaps);
	UserLogins getRolesByUserId(UserRoleMap roleMaps);
}
