package srdt.co.in.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class RolePermission {

	@Schema(description="Not null or blank during mapping" , required=true)
	private long roleid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private List<Long> permissionid = new ArrayList<>();
	private long rolepermissionmapid;
	@Schema(description="Not null or blank during mapping" , required=true)
	private String createdby;
	
	public RolePermission() {
		
	}

	public RolePermission(long roleid) {
		
		this.roleid = roleid;
	}

	public RolePermission(ArrayList<Long> permissionid, long rolepermissionmapid) {
		
		this.permissionid = permissionid;
		this.rolepermissionmapid = rolepermissionmapid;
	}
    public RolePermission(ArrayList<Long> permissionid, long rolepermissionmapid,String createdby) {
		
		this.permissionid = permissionid;
		this.rolepermissionmapid = rolepermissionmapid;
		this.createdby = createdby;
	}
    
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public List<Long> getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(ArrayList<Long> permissionid) {
		this.permissionid = permissionid;
	}

	public long getRolepermissionmapid() {
		return rolepermissionmapid;
	}

	public void setRolepermissionmapid(long rolepermissionmapid) {
		this.rolepermissionmapid = rolepermissionmapid;
	}	
}
