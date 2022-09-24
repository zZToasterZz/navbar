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
@Table(name = "Permission",uniqueConstraints = @UniqueConstraint(columnNames = {"PermissionName"}))
@NamedQueries(
		{
			@NamedQuery(name = "AllPermissions",query = "select p from Permissions p"),
			@NamedQuery(name = "SearchPermission",query = "select p from Permissions p where p.PermissionName like CONCAT('%',:Name,'%')"),
			@NamedQuery(name = "SaveAsPermission", query = "select p from PermissionNavBarMaping p inner join p.permissions q where q.PermissionId=:PermissionId"),
			@NamedQuery(name = "GetPermissionsByRoleId", query = "select p from Permissions p inner join p.rolePermissionMappings q where q.roles.RoleId=:RoleId")
		})
public class Permissions extends SharedField implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long PermissionId;
	@Column(length = 100)
	@NotNull
	private String PermissionName;
	
	@OneToMany(mappedBy = "permissions",cascade = CascadeType.ALL)
	private List<RolePermissionMapping> rolePermissionMappings = new ArrayList<RolePermissionMapping>();
	
	@OneToMany(mappedBy = "permissions",cascade = CascadeType.ALL)
	private List<PermissionNavBarMaping> navBarMapings = new ArrayList<>();
	
	public Permissions() {
		
	}
	
	public Permissions(String permissionName,String createdBy) {
		
		PermissionName = permissionName;
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
	}

	public Permissions(String permissionName,String createdBy,List<RolePermissionMapping> rolePermissionMappings) {
		
		CreatedBy = createdBy;
		PermissionName = permissionName;
		ModifiedBy = createdBy;
		this.rolePermissionMappings = rolePermissionMappings;
	}
     
	public Permissions(long permissionId, @NotNull String permissionName,
			List<RolePermissionMapping> rolePermissionMappings, List<PermissionNavBarMaping> navBarMapings,String createdBy) {
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
		PermissionId = permissionId;
		PermissionName = permissionName;
		this.rolePermissionMappings = rolePermissionMappings;
		this.navBarMapings = navBarMapings;
	}

	public long getPermissionId() {
		return PermissionId;
	}
	public void setPermissionId(long permissionId) {
		PermissionId = permissionId;
	}
	public String getPermissionName() {
		return PermissionName;
	}
	public void setPermissionName(String permissionName) {
		PermissionName = permissionName;
	}

	public List<RolePermissionMapping> getRolePermissionMappings() {
		return rolePermissionMappings;
	}

	public void setRolePermissionMappings(List<RolePermissionMapping> rolePermissionMappings) {
		this.rolePermissionMappings = rolePermissionMappings;
	}

	public List<PermissionNavBarMaping> getNavBarMapings() {
		return navBarMapings;
	}

	public void setNavBarMapings(List<PermissionNavBarMaping> navBarMapings) {
		this.navBarMapings = navBarMapings;
	}
	
}
