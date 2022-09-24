package srdt.co.in.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Roles",uniqueConstraints = @UniqueConstraint(columnNames = { "RoleName" }))
@NamedQueries(
		{
			@NamedQuery(name = "AllRoles" , query = "select r from Roles r"),
			@NamedQuery(name = "GetRoleById",query = "select r from Roles r where r.RoleId=:RoleId"),
			@NamedQuery(name = "SearchRoles" , query = "select r from Roles r where r.RoleName like CONCAT('%',:RoleName,'%')"),
			@NamedQuery(name = "SaveAsRole", query = "select r from RolePermissionMapping r inner join r.roles s where s.RoleId=:RoleId"),
			@NamedQuery(name = "GetRolesByLoginId", query = "select r from Roles r inner join r.userRoleMappings s where s.userLogins.LoginId=:LoginId")
		})
public class Roles extends SharedField implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long RoleId;
	@Column(length = 50)
	@NotNull
	private String RoleName;
	
	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private List<RolePermissionMapping> rolePermissionMappings = new ArrayList<RolePermissionMapping>();
	
	@OneToMany(mappedBy = "roles",cascade = CascadeType.ALL)
	private List<UserRoleMappings> userRoleMappings = new ArrayList<>();
	
	public Roles() {
		
	}
	public Roles(String roleName, String createdBy) {
		
		RoleName = roleName;
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
	}
	
	public Roles(String roleName, String createdBy, List<RolePermissionMapping> rolePermissionMappings) {
		
		RoleName = roleName;
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
		this.rolePermissionMappings = rolePermissionMappings;
	}
	public long getRoleId() {
		return RoleId;
	}
	public void setRoleId(long roleId) {
		RoleId = roleId;
	}
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public List<RolePermissionMapping> getRolePermissionMappings() {
		return rolePermissionMappings;
	}
	public void setRolePermissionMappings(List<RolePermissionMapping> rolePermissionMappings) {
		this.rolePermissionMappings = rolePermissionMappings;
	}	
}
