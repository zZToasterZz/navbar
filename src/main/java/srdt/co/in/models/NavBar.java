package srdt.co.in.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class NavBar {
	
	private long navbarid;
	@Schema(description="Not null or blank during navbar creation" , required=true)
	private String navname;
	@Schema(description="Not null or blank during navbar creation" , required=true)
	private String navdescr;
	@Schema(description="Not null or blank during navbar creation,. Parentid 0 represt root parent" , required=true)
	private long parentid;
	@Schema(description="Not null or blank during navbar creation. Values for isparent Y or N" , required=true)
	private String isparent;
	@Schema(description="Not null or blank during navbar creation. If you create isparent Y then requestaddr must be blank but not null" , required=true)
	private String requestaddr;
	@Schema(description="Not null or blank during navbar creation" , required=true)
	private String createdby;
	private String isactive;
	
	public NavBar() {
		
	}
	public NavBar(String navname, String navdescr, long parentid, String isparent, String requestaddr, String createdby) {
		
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
	}
	
	public NavBar(long navbarid, String navname, String navdescr, long parentid, String isparent, String requestaddr) {
		
		this.navbarid = navbarid;
		this.navname = navname;
		this.navdescr = navdescr;
		this.parentid = parentid;
		this.isparent = isparent;
		this.requestaddr = requestaddr;
	}
	
	public NavBar(long navbarid, String navdescr, String requestaddr, String createdby) {
		
		this.navbarid = navbarid;
		this.navdescr = navdescr;
		this.requestaddr = requestaddr;
		this.createdby = createdby;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public long getNavbarid() {
		return navbarid;
	}
	public void setNavbarid(long navbarid) {
		this.navbarid = navbarid;
	}
	public String getNavname() {
		return navname;
	}
	public void setNavname(String navname) {
		this.navname = navname;
	}
	public String getNavdescr() {
		return navdescr;
	}
	public void setNavdescr(String navdescr) {
		this.navdescr = navdescr;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
	}
	public String getIsparent() {
		return isparent;
	}
	public void setIsparent(String isparent) {
		this.isparent = isparent;
	}
	public String getRequestaddr() {
		return requestaddr;
	}
	public void setRequestaddr(String requestaddr) {
		this.requestaddr = requestaddr;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}	
}
