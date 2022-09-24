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
@Table(name = "NavBar",uniqueConstraints = @UniqueConstraint(columnNames = { "NavName" }))
@NamedQueries(
		{
			@NamedQuery(name = "AllRootParent",query = "select n from NavBars n where n.IsParent='Y' and n.ParentId=0"),
			@NamedQuery(name = "NavBars.AllParents",query = "select n from NavBars n where n.IsParent='Y' and n.ParentId!=0"),
			@NamedQuery(name = "GetChildByParentId",query = "select n from NavBars n where n.ParentId=:ParentId"),
			@NamedQuery(name = "GetParentByChildId" , query = "select n from NavBars n where n.ParentId in(select ParentId from NavBars where NavBarId in " 
															  +"(select ParentId from NavBars where NavBarId=:NavBarId))"),
			@NamedQuery(name = "AllRootParentByPermissionId",query = "select distinct n from NavBars n left join n.navBarMapings m where n.IsParent='Y' "
																	 +"and n.ParentId=0 and m.permissions.PermissionId in(:PermissionId)"),
			@NamedQuery(name = "GetChildByParentIdByPermissionId",query = "select distinct n from NavBars n left join n.navBarMapings m where n.ParentId=:ParentId and m.permissions.PermissionId in(:PermissionId)"),
			@NamedQuery(name = "GetParentByChildIdByPermissionId" , query = "select distinct n from NavBars n left join n.navBarMapings m where m.permissions.PermissionId in(:PermissionId) and n.ParentId in(select ParentId from NavBars where NavBarId in " 
															  +"(select ParentId from NavBars where NavBarId=:NavBarId))"),
			@NamedQuery(name="GetNavBarByPermissionId", query = "select distinct n from NavBars n inner join n.navBarMapings o where o.permissions.PermissionId=:PermissionId"),
			@NamedQuery(name = "NavBars.getAllNodes",query = "select n from NavBars n")
		})
public class NavBars extends SharedField implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long NavBarId ;
	@Column(length = 50)
	@NotNull
	private String NavName ;
	@Column(length = 100)
	@NotNull
	private String NavDescr ;
	@Column
	@NotNull
	private long ParentId ;
	@Column(length = 1)
	@NotNull
	private String IsParent ;
	@Column(length = 150)
	private String RequestAddr ;
	
	@OneToMany(mappedBy = "navBars",cascade = CascadeType.ALL)
	private List<PermissionNavBarMaping> navBarMapings = new ArrayList<>();
	
	public NavBars() {
		
	}
	public NavBars(String navName, String navDescr, long parentId, String isParent, String requestAddr) {
		
		NavName = navName;
		NavDescr = navDescr;
		ParentId = parentId;
		IsParent = isParent;
		RequestAddr = requestAddr;
	}
	public NavBars(List<PermissionNavBarMaping> navBarMapings ,String navName, String navDescr, long parentId, String isParent, String requestAddr,String creayedBy) {
		
		NavName = navName;
		NavDescr = navDescr;
		ParentId = parentId;
		IsParent = isParent;
		RequestAddr = requestAddr;
		CreatedBy = creayedBy;
		ModifiedBy = creayedBy;
		this.navBarMapings = navBarMapings;
	}
	public NavBars(String navName, String navDescr, long parentId, String isParent, String requestAddr,String creayedBy) {
		
		NavName = navName;
		NavDescr = navDescr;
		ParentId = parentId;
		IsParent = isParent;
		RequestAddr = requestAddr;
		CreatedBy = creayedBy;
		ModifiedBy = creayedBy;
	}
	public long getNavBarId() {
		return NavBarId;
	}
	public void setNavBarId(long navBarId) {
		NavBarId = navBarId;
	}
	public String getNavName() {
		return NavName;
	}
	public void setNavName(String navName) {
		NavName = navName;
	}
	public String getNavDescr() {
		return NavDescr;
	}
	public void setNavDescr(String navDescr) {
		NavDescr = navDescr;
	}
	public long getParentId() {
		return ParentId;
	}
	public void setParentId(long parentId) {
		ParentId = parentId;
	}
	public String getIsParent() {
		return IsParent;
	}
	public void setIsParent(String isParent) {
		IsParent = isParent;
	}
	public String getRequestAddr() {
		return RequestAddr;
	}
	public void setRequestAddr(String requestAddr) {
		RequestAddr = requestAddr;
	}
	public List<PermissionNavBarMaping> getNavBarMapings() {
		return navBarMapings;
	}
	public void setNavBarMapings(List<PermissionNavBarMaping> navBarMapings) {
		this.navBarMapings = navBarMapings;
	}	
}
