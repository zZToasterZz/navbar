package srdt.co.in.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UserLogin")
@NamedQueries({
	@NamedQuery(name = "AllLogins",query = "select ul from UserLogins ul"),
	@NamedQuery(name = "ValidateUser" , query = "select ul from UserLogins ul where ul.IsActive='Y' and ul.LoginId=:LoginId and Pwd=:Pwd"),
	@NamedQuery(name = "ValidateUserByLoginId" , query = "select ul from UserLogins ul where ul.IsActive='Y' and ul.LoginId=:LoginId"),
	@NamedQuery(name = "searchuserlogins" , query = "select ul from UserLogins ul where ul.LoginId like CONCAT('%',:LoginId,'%') and "
			                                    + "ul.EmplId like CONCAT('%',:EmplId,'%') and "
			                                    + "ul.EmailId like CONCAT('%',:EmailId,'%')"),
	@NamedQuery(name = "ChangePwd", query = "update UserLogins set Pwd=:pwd where LoginId=:loginid")
})
public class UserLogins extends SharedField implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	@Column(length = 50,unique = true)
	@NotNull
	private String LoginId;
	@Column(length = 30,unique = true)
	@NotNull
	private String EmplId;
	@Column(length = 150)
	@NotNull
	@Email(message = "Email Id is invalid")
	private String EmailId;
	@Column(length = 100)
	@NotNull
	private String Pwd;//AES.encrypt(Generation.generatePassword(), Generation.getSecretKey()) ;
	@Column(length = 1)
	@NotNull
	private String IsEncrypt = "Y";
	
	@OneToMany(mappedBy = "userLogins",cascade = CascadeType.ALL)
	List<UserRoleMappings> roleMappings = new ArrayList<>();
	
	public UserLogins() {
		
	}
    public UserLogins(UserLogins user) {
    	LoginId = user.getLoginId();
		EmplId = user.getEmplId();
		EmailId = user.getEmailId();
		CreatedBy = user.getCreatedBy();
		ModifiedBy = user.getModifiedBy();
		Pwd = user.getPwd();
	}
	public UserLogins(String loginId, String emplId, String emailId) {
		
		LoginId = loginId;
		EmplId = emplId;
		EmailId = emailId;
	}
	public UserLogins(String loginId, String emplId, String emailId,String createdby) {
		
		LoginId = loginId;
		EmplId = emplId;
		EmailId = emailId;
		CreatedBy = createdby;
		ModifiedBy = createdby;
	}
    public UserLogins(String loginId, String emplId, String emailId,String createdby,String pwd) {
		
		LoginId = loginId;
		EmplId = emplId;
		EmailId = emailId;
		CreatedBy = createdby;
		ModifiedBy = createdby;
		Pwd = pwd;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getLoginId() {
		return LoginId;
	}
	public void setLoginId(String loginId) {
		LoginId = loginId;
	}
	public String getEmplId() {
		return EmplId;
	}
	public void setEmplId(String emplId) {
		EmplId = emplId;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getIsEncrypt() {
		return IsEncrypt;
	}
	public void setIsEncrypt(String isEncrypt) {
		IsEncrypt = isEncrypt;
	}
	public List<UserRoleMappings> getRoleMappings() {
		return roleMappings;
	}
	public void setRoleMappings(List<UserRoleMappings> roleMappings) {
		this.roleMappings = roleMappings;
	}	
}
