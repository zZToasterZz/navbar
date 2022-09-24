package srdt.co.in.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import srdt.co.in.models.NavBarMap;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.repositories.PermissionNavBarMappingRepository;

@RestController
@RequestMapping("/api/navbarmapping")
public class PermissionNavBarMappingResource {

	@Autowired
	PermissionNavBarMappingRepository service;
	
	@PostMapping("/mapnavbar")
	public ResponceMessage navBarMapping(@RequestBody NavBarMap navBarMap)
	{
		return service.navBarMapping(navBarMap);
	}
	
	@PostMapping("/unmapnavbar")
	public ResponceMessage unMapNavBarMapping(@RequestBody NavBarMap navBarMap)
	{
		return service.unMapNavBarMapping(navBarMap);
	}
}
