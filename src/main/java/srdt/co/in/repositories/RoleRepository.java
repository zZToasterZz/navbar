package srdt.co.in.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import srdt.co.in.entities.Roles;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.Role;

@Repository
public interface RoleRepository {

 	ResponceMessage createRole(List<Role> roles);
 	ResponceMessage removeRole(List<Role> roleid);
 	List<Roles> getRoles();
    Roles getRoleById(long roleid); 
    List<Roles> search(Role role);
    ResponceMessage saveAs(Role role);
    List<Roles> getUserRolesByLoginId(String loginid);
}
