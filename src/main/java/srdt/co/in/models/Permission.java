package srdt.co.in.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class Permission {
	
	private long permissionid;
	@Schema(description="Not null or blank during permission creation" , required=true)
	private String permissionname;
	@Schema(description="Not null or blank during permission creation" , required=true)
	private String createdby;
	@Schema(description="Not require during permission creation" , required=false)
	private String isactive;
	
	public Permission() {
		
	}

	public Permission(String permissionname, String createdby) {
		
		this.permissionname = permissionname;
		this.createdby = createdby;
	}

	public Permission(long permissionid, String permissionname, String createdby, String isactive) {
		
		this.permissionid = permissionid;
		this.permissionname = permissionname;
		this.createdby = createdby;
		this.isactive = isactive;
	}

	public long getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
}
