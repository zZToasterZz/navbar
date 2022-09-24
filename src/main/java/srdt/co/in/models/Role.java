package srdt.co.in.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class Role {
	
	private long roleid;
	@Schema(description="Not null or blank during role creation" , required=true)
	private String rolename;
	@Schema(description="Not null or blank during role creation" , required=true)
	private String createdby;
	@Schema(description="Not require during role creation" , required=false)
	private String isactive;
	
	public Role() {
		
	}
	
	public Role(String rolename, String createdby) {
		
		this.rolename = rolename;
		this.createdby = createdby;
	}
	
	public Role(long roleid, String rolename, String createdby, String isactive) {
		
		this.roleid = roleid;
		this.rolename = rolename;
		this.createdby = createdby;
		this.isactive = isactive;
	}

	public long getRoleId() {
		return roleid;
	}

	public void setRoleId(long roleid) {
		this.roleid = roleid;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
}
