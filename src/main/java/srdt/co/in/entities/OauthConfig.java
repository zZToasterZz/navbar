package srdt.co.in.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="OauthConfig")
@NamedQueries(
		{
			@NamedQuery(name="GetOauthConfig", query="select o from OauthConfig o where o.IsActive='Y' order by o.OauthConfigId desc")
		})
public class OauthConfig {

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="OauthConfig_Sqr",sequenceName="OauthConfig_Sqr")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="OauthConfig_Sqr")
	@Column
	private int OauthConfigId;
	@Column(length=50,unique=true)
	private String UserName;
	@Column(length=50)
	private String Pwd;
	@Column(length=150)
	private String Server;
	@Column(length=1)
	private String IsActive="Y";
	
	public OauthConfig() {
		super();
	}

	public OauthConfig(String userName, String pwd, String server) {
		super();
		UserName = userName;
		Pwd = pwd;
		Server = server;
	}

	public int getOauthConfigId() {
		return OauthConfigId;
	}

	public void setOauthConfigId(int oauthConfigId) {
		OauthConfigId = oauthConfigId;
	}

	public String getUserNmae() {
		return UserName;
	}

	public void setUserNmae(String userName) {
		UserName = userName;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	public String getServer() {
		return Server;
	}

	public void setServer(String server) {
		Server = server;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}	
}
