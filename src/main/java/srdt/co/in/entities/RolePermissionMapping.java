package srdt.co.in.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RolePermissionMapping")
public class RolePermissionMapping extends SharedField implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long RolePermissionMapId;
	@ManyToOne
	@JoinColumn(name = "RoleId",referencedColumnName = "RoleId",insertable = true,updatable = true,nullable = false)
	private Roles roles = new Roles();
	@ManyToOne
	@JoinColumn(name = "PermissionId" , referencedColumnName = "PermissionId",insertable = true,updatable = true,nullable = false)
	private Permissions permissions = new Permissions();
	
	public RolePermissionMapping() {
		
	}
	public RolePermissionMapping(Roles roles, Permissions permissions,String createdBy) {
		
		this.roles = roles;
		this.permissions = permissions;
		CreatedBy = createdBy;
		ModifiedBy = createdBy;
	}
	public long getRolePermissionMapId() {
		return RolePermissionMapId;
	}
	public void setRolePermissionMapId(long rolePermissionMapId) {
		RolePermissionMapId = rolePermissionMapId;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public Permissions getPermissions() {
		return permissions;
	}
	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
}
