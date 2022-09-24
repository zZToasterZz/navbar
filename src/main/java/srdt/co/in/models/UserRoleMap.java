package srdt.co.in.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;


public class UserRoleMap {
	
	private long rolemapid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private long loginid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private long userid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private String createdby;
	@Schema(description="Not null or blank during mapping" , required=true)
	private List<Long> actionids = new ArrayList<>();
	private UserLogin userLogins = new UserLogin(); 
	private List<Role> roles = new ArrayList<>();
	public UserRoleMap() {
		
	}
	
	public UserRoleMap(long loginid) {
		
		this.loginid = loginid;
	}

	public UserRoleMap(String createdby, List<Long> actionids) {
		
		this.createdby = createdby;
		this.actionids = actionids;
	}

	
	public UserRoleMap(List<Role> roles,String createdby) {
	  
		this.createdby = createdby; 
		this.roles = roles; 
	  
	}
	public UserRoleMap(long userid, String createdby, List<Long> actionids) {
		
		this.userid = userid;
		this.createdby = createdby;
		this.actionids = actionids;
	}
	public UserRoleMap(String createdby, List<Long> actionids, UserLogin userLogins, List<Role> roles) {
		
		this.createdby = createdby;
		this.actionids = actionids;
		this.userLogins = userLogins;
		this.roles = roles;
	}
	public UserRoleMap(long rolemapid, String createdby, List<Long> actionids, UserLogin userLogins, List<Role> roles) {
		
		this.rolemapid = rolemapid;
		this.createdby = createdby;
		this.actionids = actionids;
		this.userLogins = userLogins;
		this.roles = roles;
	}
	
	public long getRoleMapId() {
		return rolemapid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public UserLogin getUserLogins() {
		return userLogins;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public List<Long> getActionids() {
		return actionids;
	}
	public void setActionids(List<Long> actionids) {
		this.actionids = actionids;
	}

	public long getLoginid() {
		return loginid;
	}

	public void setLoginid(long loginid) {
		this.loginid = loginid;
	}
}
