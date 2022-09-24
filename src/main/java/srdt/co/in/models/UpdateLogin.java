package srdt.co.in.models;

import java.util.ArrayList;
import java.util.List;

public class UpdateLogin {
	
	private String loginid;
	private String emplid;
	private String emailid;
	private String pwd;
	private Long userid;
	private String updatedby;
	
	private List<Long> roleid = new ArrayList<>();
	
	public UpdateLogin() {
		super();
	}

	public UpdateLogin(String loginid, String emplid, String emailid, String pwd, Long userid, List<Long> roleid) {
		super();
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.userid = userid;
		this.roleid = roleid;
	}

	public UpdateLogin(String loginid, String emplid, String emailid, String pwd, List<Long> roleid) {
		super();
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.roleid = roleid;
	}

	public UpdateLogin(String loginid, String emplid, String emailid, String pwd, Long userid, String updatedby,
			List<Long> roleid) {
		super();
		this.loginid = loginid;
		this.emplid = emplid;
		this.emailid = emailid;
		this.pwd = pwd;
		this.userid = userid;
		this.updatedby = updatedby;
		this.roleid = roleid;
	}
    
	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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

	public List<Long> getRoleid() {
		return roleid;
	}

	public void setRoleid(List<Long> roleid) {
		this.roleid = roleid;
	}
	

}
