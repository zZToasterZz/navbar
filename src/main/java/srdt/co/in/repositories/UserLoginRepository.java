package srdt.co.in.repositories;

import java.text.ParseException;
import java.util.List;

import srdt.co.in.entities.Roles;
import srdt.co.in.entities.UserLogins;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.UpdateLogin;
import srdt.co.in.models.UserLogin;

public interface UserLoginRepository {
	
	ResponceMessage createLogin(UserLogin user);
	List<UserLogins> getLogins();
	UserLogins getLoginById(long Id);
	UserLogins getLoginByLoginId(String loginid);
	List<Roles> getUserRolesById(long Id);
	ResponceMessage updateLoginById(UserLogin user);
	ResponceMessage desableLoginById(UserLogin user);
	ResponceMessage desableLogins(List<UserLogin> user);
	UserLogins ValidateUser(UserLogin login);
	ResponceMessage resetPwd(UserLogin login);
	List<UserLogins> search(UserLogin user);
	
	ResponceMessage updatelogin(UpdateLogin updateLogin) throws ParseException;
	void changePwd(String pwd, String loginid);
}
