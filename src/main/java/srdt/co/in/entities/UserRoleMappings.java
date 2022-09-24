package srdt.co.in.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UserRoleMapping"/*,uniqueConstraints = @UniqueConstraint(columnNames = {"LoginId","RoleId"})*/)
@NamedNativeQueries(
		{
			@NamedNativeQuery(name="GetRoleMappingByLoginId",query="select u from UserRoleMappings u")
		})
public class UserRoleMappings extends SharedField implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1,name = "UserRoleMapping_Seq",sequenceName = "UserRoleMapping_Seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserRoleMapping_Seq")
	@Column
	private long RoleMapId;
	@ManyToOne
	@JoinColumn(name = "LoginId", referencedColumnName = "LoginId",insertable = true, updatable = true,nullable = false)
	@NotNull
	private UserLogins userLogins = new UserLogins();
	@ManyToOne
	@JoinColumn(name = "RoleId",referencedColumnName = "RoleId",insertable = true,updatable = true,nullable = false)
	@NotNull
	private Roles roles = new Roles();
	
	public UserRoleMappings() {
		
	}

	public UserRoleMappings(UserLogins userLogins, Roles roles) {
		
		this.userLogins = userLogins;
		this.roles = roles;
	}
	
	public UserRoleMappings(UserLogins userLogins, Roles roles,String CreatedBy) {
		
		this.userLogins = userLogins;
		this.roles = roles;
		this.CreatedBy = CreatedBy;
		this.ModifiedBy = CreatedBy;
	}
	
	public UserRoleMappings(long roleMapId, UserLogins userLogins, Roles roles) {
		
		RoleMapId = roleMapId;
		this.userLogins = userLogins;
		this.roles = roles;
	}

	public long getRoleMapId() {
		return RoleMapId;
	}

	public void setRoleMapId(long roleMapId) {
		RoleMapId = roleMapId;
	}

	public UserLogins getUserLogins() {
		return userLogins;
	}

	public void setUserLogins(UserLogins userLogins) {
		this.userLogins = userLogins;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
}
