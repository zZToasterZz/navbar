package srdt.co.in.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserLogin {

	private long id;
	@Schema(description="Not null or blank during login creation or login" , required=true)
	private String loginid;
	@Schema(description="Not null or blank during login creation" , required=true)
	private String emplid;
	@Schema(description="Not null or blank during login creation" , required=true)
	private String emailid;
	@Schema(description="Not null or blank during login" , required=true)
	private String pwd;
	@Schema(description="Not null or blank during login creation" , required=true)
	private String createdby;
	private String isactive;
	private String access_tocken;
	public UserLogin() {
		
	}
	
	public UserLogin(long id, String createdby) {
		
		this.id = id;
		this.createdby = createdby;
	}

	public UserLogin(long id, String emailid, String pwd, String createdby) {
		
		this.id = id;
		this.emailid = emailid;
		this.pwd = pwd;
		this.createdby = createdby;
	}

	public UserLogin(long id, String loginid, String emplid, String emailid, String pwd) {
		
		this.id = id;
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
	}

	public UserLogin(String loginid, String emplid, String emailid, String createdby) {
		
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.createdby = createdby;
	}

	public UserLogin(long id, String loginid, String emplid, String emailid, String pwd, String createdby) {
		
		this.id = id;
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.createdby = createdby;
	}

	public UserLogin(long id, String loginid, String emplid, String emailid, String pwd, String createdby,
			String isactive) {
		
		this.id = id;
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.createdby = createdby;
		this.isactive = isactive;
	}

	public String getAccess_tocken() {
		return access_tocken;
	}

	public void setAccess_tocken(String access_tocken) {
		this.access_tocken = access_tocken;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
