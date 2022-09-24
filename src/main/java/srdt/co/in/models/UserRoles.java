package srdt.co.in.models;

public class UserRoles {

	private long rolemapid;
	private String loginid;
	private String createdby;
	private String rolename;
	public UserRoles(long rolemapid, String loginid, String createdby, String rolename) {
		
		this.rolemapid = rolemapid;
		this.loginid = loginid;
		this.createdby = createdby;
		this.rolename = rolename;
	}
	public long getRolemapid() {
		return rolemapid;
	}
	public void setRolemapid(long rolemapid) {
		this.rolemapid = rolemapid;
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
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}	
}
