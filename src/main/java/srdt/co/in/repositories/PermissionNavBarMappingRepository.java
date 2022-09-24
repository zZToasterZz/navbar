package srdt.co.in.repositories;

import org.springframework.stereotype.Repository;

import srdt.co.in.models.NavBarMap;
import srdt.co.in.models.ResponceMessage;

@Repository
public interface PermissionNavBarMappingRepository {

	ResponceMessage navBarMapping(NavBarMap navBarMap);
	ResponceMessage unMapNavBarMapping(NavBarMap navBarMap);
	ResponceMessage updateNavBarMapping(NavBarMap navBarMap);
}
