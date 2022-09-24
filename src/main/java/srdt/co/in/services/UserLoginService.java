package srdt.co.in.services;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.Roles;
import srdt.co.in.entities.UserLogins;
import srdt.co.in.entities.UserRoleMappings;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.UpdateLogin;
import srdt.co.in.models.UserLogin;
import srdt.co.in.repositories.UserLoginRepository;
import srdt.co.in.utility.AES;
import srdt.co.in.utility.Generation;

@Service
public class UserLoginService implements UserLoginRepository {

	@Autowired
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public ResponceMessage createLogin(UserLogin user) {
		// TODO Auto-generated method stub
		em.persist(new UserLogins(user.getLoginid(), user.getEmplid(), user.getEmailid(), user.getCreatedby(),user.getPwd()));
		em.close();
		return new ResponceMessage("Login Created");
	}

	@Override
	public List<UserLogins> getLogins() {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("AllLogins");
		List<UserLogins> logins = extracted(query);
		em.close();
		return logins;
	}

	@Override
	public UserLogins getLoginById(long Id) {
		// TODO Auto-generated method stub
		UserLogins login = em.find(UserLogins.class, Id);
		em.close();
		return login;
	}

	@Override
	public List<Roles> getUserRolesById(long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ResponceMessage updateLoginById(UserLogin user) {
		// TODO Auto-generated method stub
		UserLogins login = em.find(UserLogins.class, user.getId());
		if(login != null)
		{
			login.setModifiedBy(user.getCreatedby());
			login.setPwd(AES.encrypt(user.getPwd(), Generation.getSecretKey()));
			login.setEmailId(user.getEmailid());
			em.persist(login);
		}
		em.close();
		return new ResponceMessage("Details Updated");
	}

	@Override
	@Transactional
	public ResponceMessage desableLoginById(UserLogin user) {
		// TODO Auto-generated method stub
		UserLogins login = em.find(UserLogins.class, user.getId());
		if(login != null)
		{
			login.setIsActive(user.getIsactive());
			em.persist(login);
		}
		em.close();
		return new ResponceMessage("Login Desabled");
	}

	@Override
	@Transactional
	public ResponceMessage desableLogins(List<UserLogin> user) {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		user.stream()
		    .forEach(x->
		    {
		    	UserLogins login = em.find(UserLogins.class, x.getId());
				if(login != null)
				{
					login.setIsActive(x.getIsactive());
					em.persist(login);
				}
		    });
		em.close();
		return new ResponceMessage("Logins Desabled");
	}
	
	@SuppressWarnings("unchecked")
	private List<UserLogins> extracted(Query query) {
		return (List<UserLogins>)query.getResultList();
	}

	@Override
	public UserLogins ValidateUser(UserLogin login) {
		// TODO Auto-generated method stub
		
		List<UserLogins> userLogins = null;
		Query query = em.createNamedQuery("ValidateUser");
		query.setParameter("LoginId", login.getLoginid());
		query.setParameter("Pwd", login.getPwd());
		userLogins = extracted(query);
		if(userLogins != null && userLogins.size() != 0)
		{
			return userLogins.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public ResponceMessage resetPwd(UserLogin loginid) {
		// TODO Auto-generated method stub
		UserLogins user = em.find(UserLogins.class, loginid.getId());
		if(user != null)
		{			
			user.setPwd(loginid.getPwd());
			em.persist(user);
			em.close();
			return new ResponceMessage("Password reset successfully and your default password is "+Generation.getDefaultPwd());
		}
		em.close();
		return new ResponceMessage("User Not Found");
	}

	@Override
	public UserLogins getLoginByLoginId(String loginid) {
		// TODO Auto-generated method stub
		
		List<UserLogins> userLogins = null;
		Query query = em.createNamedQuery("ValidateUserByLoginId");
		query.setParameter("LoginId", loginid);
		userLogins = extracted(query);
		if(userLogins != null && userLogins.size() != 0)
		{
			return userLogins.get(0);
		}
		return null;
	}

	@Override
	public List<UserLogins> search(UserLogin user) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("searchuserlogins");
		query.setParameter("EmailId", user.getEmailid());
		query.setParameter("EmplId", user.getEmplid());
		query.setParameter("LoginId", user.getLoginid());
		List<UserLogins> logins = extracted(query);
		em.close();
		return logins;
	}

	@Override
	@Transactional
	public ResponceMessage updatelogin(UpdateLogin updateLogin) throws ParseException {
		// TODO Auto-generated method stub
		em.unwrap(Session.class).setJdbcBatchSize(10);
		UserLogins userLogins = em.find(UserLogins.class, updateLogin.getUserid());
		if(updateLogin.getPwd() != null && !updateLogin.getPwd().equals(""))
		{
			userLogins.setPwd(updateLogin.getPwd());
		}
		userLogins.setEmailId(updateLogin.getEmailid());
		userLogins.setLastUpdatedDate(Generation.getCurrentDate());
		userLogins.setEmplId(updateLogin.getEmplid());
		userLogins.setLoginId(updateLogin.getLoginid());
		em.persist(userLogins);
		String sql = "delete from user_role_mapping where login_id=:loginid and role_id not in(:roleid)";
		String sqlcount = "select count(*) from user_role_mapping where login_id=:loginid and role_id=:roleid";
		Query query = em.createNativeQuery(sql);
		query.setParameter("loginid", updateLogin.getLoginid());
		query.setParameter("roleid", updateLogin.getRoleid());
		query.executeUpdate();
		updateLogin.getRoleid()
		           .stream()
		           .forEach(x->
		           {
		        	   Query querycount = em.createNativeQuery(sqlcount);
		        	   querycount.setParameter("loginid", updateLogin.getLoginid());
		        	   querycount.setParameter("roleid", x);
		        	   BigInteger flag =  (BigInteger) querycount.getResultList().get(0);
		        	   if(flag.intValue() == 0)
		        	   {
		        		   Roles role = em.find(Roles.class,x);
		        		   if(role != null)
		        		   {
		        			   em.persist(new UserRoleMappings(userLogins, role,updateLogin.getUpdatedby()));
		        		   }
		        	   }
		           });
		em.flush();
		em.close();
		
		return new ResponceMessage("Details Updated");
	}
	
	@Override
	@Transactional
	public void changePwd(String pwd, String loginid)
	{
		em.createNamedQuery("ChangePwd")
		                                .setParameter("pwd", pwd)
		                                .setParameter("loginid", loginid)
		                                .executeUpdate();
	 
	}
}
