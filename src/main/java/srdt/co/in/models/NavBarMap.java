package srdt.co.in.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class NavBarMap {

	private long permissionid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private List<Long> navbarid = new ArrayList<>();
	private long navbarmapid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private String loginid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private String createdby;
	
	public NavBarMap() {
		
	}	
	public NavBarMap(String loginid) {
		
		this.loginid = loginid;
	}
	public NavBarMap(long permissionid, List<Long> navbarid,String createdby) {
		
		this.permissionid = permissionid;
		this.navbarid = navbarid;
		this.createdby = createdby;
	}
	public NavBarMap(long permissionid, List<Long> navbarid, long navbarmapid,String createdby) {
		
		this.permissionid = permissionid;
		this.navbarid = navbarid;
		this.navbarmapid = navbarmapid;
		this.createdby = createdby;
	}
	
	public long getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}
	public List<Long> getNavbarid() {
		return navbarid;
	}
	public void setNavbarid(List<Long> navbarid) {
		this.navbarid = navbarid;
	}
	public long getNavbarmapid() {
		return navbarmapid;
	}
	public void setNavbarmapid(long navbarmapid) {
		this.navbarmapid = navbarmapid;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}	
}
