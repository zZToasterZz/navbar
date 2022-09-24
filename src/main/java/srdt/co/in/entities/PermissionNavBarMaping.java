package srdt.co.in.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PermissionNavBarMaping")
public class PermissionNavBarMaping extends SharedField implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(allocationSize = 1,initialValue = 1,name = "PermissionNavBarMaping_Seq",sequenceName = "PermissionNavBarMaping_Seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PermissionNavBarMaping_Seq")
	@Column
	private long NavBarMapId;
	@ManyToOne
	@JoinColumn(name = "NavBarId",referencedColumnName = "NavBarId",insertable = true,updatable = true,nullable = false)
	private NavBars navBars = new NavBars();
	@ManyToOne
	@JoinColumn(name = "PermissionId",referencedColumnName = "PermissionId",insertable = true,updatable = true,nullable = false)
	private Permissions permissions = new Permissions();
	
	public PermissionNavBarMaping() {
		
	}

	public PermissionNavBarMaping(NavBars navBars, Permissions permissions) {
		
		this.navBars = navBars;
		this.permissions = permissions;
	}
	
	public PermissionNavBarMaping(NavBars navBars, Permissions permissions,String createdby) {
		
		this.navBars = navBars;
		this.permissions = permissions;
		this.CreatedBy = createdby;
		this.ModifiedBy = createdby;
	}

	public long getNavBarMapId() {
		return NavBarMapId;
	}

	public void setNavBarMapId(long navBarMapId) {
		NavBarMapId = navBarMapId;
	}

	public NavBars getNavBars() {
		return navBars;
	}

	public void setNavBars(NavBars navBars) {
		this.navBars = navBars;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}	
}
