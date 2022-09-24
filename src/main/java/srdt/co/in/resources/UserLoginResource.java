package srdt.co.in.resources;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import srdt.co.in.entities.UserLogins;
import srdt.co.in.models.LoginTocken;
import srdt.co.in.models.ResponceMessage;
import srdt.co.in.models.Role;
import srdt.co.in.models.UpdateLogin;
import srdt.co.in.models.UserLogin;
import srdt.co.in.repositories.UserLoginRepository;
import srdt.co.in.utility.Generation;

@RestController
@RequestMapping("/api/userlogin")
public class UserLoginResource {
	
	@Autowired
	UserLoginRepository service;
	
	@Autowired
    private PasswordEncoder passwordEncoder;	
	
	RestTemplate rest = new RestTemplate();
	

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;
    
    @Value("${unif.id}")
    private String unifid;
    @Value("${unif.pwd}")
    private String unifpwd;
	
	@PostMapping("/createlogin")
	public ResponceMessage createLogin(@RequestBody UserLogin user)
	{
		String defaultpwd = Generation.generatePassword();
		user.setPwd(passwordEncoder.encode(defaultpwd));
		ResponceMessage responce = service.createLogin(user);
		responce.setMessage(responce.getMessage() + " and default password is " + defaultpwd);
		return responce;
	}
	
	@PostMapping("/synclogins")
	public List<ResponceMessage> syncLogin(@RequestBody List<UserLogin> users)
	{		
		String defaultpwd = Generation.getDefaultPwd();
		
		List<ResponceMessage> responces = users.stream().map(user->{
			ResponceMessage responce = new ResponceMessage();
			try
			{
				user.setPwd(passwordEncoder.encode(defaultpwd));
				responce = service.createLogin(user);
				responce.setMessage(responce.getMessage() + " with loginid= "+user.getLoginid()+" and default password is " + defaultpwd);
				return responce;
			}
			catch (Exception e) {
				return new ResponceMessage(e.getMessage());
			}
		}).collect(Collectors.toList());		
		
		return responces;
	}
	
	@GetMapping("/getlogins")
	public List<UserLogin> getLogins()
	{
		List<UserLogin> logins = new ArrayList<UserLogin>();
		logins.clear();
		service.getLogins()
			   .stream()
			   .forEach(x->
			   {
				   logins.add(new UserLogin(x.getId(), x.getLoginId(), x.getEmplId(), x.getEmailId(), x.getPwd(), x.getCreatedBy(),x.getIsActive()));
			   });
		return logins;
	}
	@GetMapping("/getloginbyid/{Id}")
	public UserLogin getLoginById(@PathVariable("Id") long Id)
	{
		UserLogins user = service.getLoginById(Id);
		return new UserLogin(user.getLoginId(), user.getEmplId(), user.getEmailId(), user.getCreatedBy());
	}
	@GetMapping("/getuserrolesbyid/{Id}")
	public List<Role> getUserRolesById(@PathVariable("Id") long Id)
	{
		return null;
	}
	@PostMapping("/updateloginbyid")
	public ResponceMessage updateLoginById(@RequestBody UserLogin user)
	{
		return service.updateLoginById(user);
	}
	@PostMapping("/desableloginbyid")
	public ResponceMessage desableLoginById(@RequestBody UserLogin user)
	{
		return service.desableLoginById(user);
	}
	@PostMapping("/desablelogins")
	public ResponceMessage desableLogins(List<UserLogin> user)
	{
		return service.desableLogins(user);
	}
	
	@PostMapping("/changepwd")
	public ResponceMessage changePwd(@RequestBody Map<String, String> pwds)
	{
		UserLogins user = service.getLoginByLoginId(pwds.get("loginid"));
		String flag = pwds.get("flag") == null ? "change" : pwds.get("flag");
		if(flag.equals("reset"))	
		{
			service.changePwd(passwordEncoder.encode(pwds.get("newpwd")), pwds.get("loginid"));
			return new ResponceMessage("success");
		}
		else if(user != null && passwordEncoder.matches(pwds.get("oldpwd"), user.getPwd()))
		{
			service.changePwd(passwordEncoder.encode(pwds.get("newpwd")), pwds.get("loginid"));
			return new ResponceMessage("success");
		}
		return new ResponceMessage("failed");
	}
	
	@PostMapping("/validateuser")
	public UserLogin ValidateUser(@RequestBody UserLogin login)
	{
		 //login.setPwd(passwordEncoder.encode(login.getPwd()));
		 UserLogins user = service.getLoginByLoginId(login.getLoginid());
		 
		 
		 
		 if(user != null && passwordEncoder.matches(login.getPwd(), user.getPwd()))
		 {
			 login.setEmailid(user.getEmailId());
			 login.setCreatedby(user.getCreatedBy());
			 login.setEmplid(user.getEmplId());
			 login.setId(user.getId());
			 
			    String credentials = unifid+":"+unifpwd;
				String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Basic " + encodedCredentials);

				HttpEntity<String> request = new HttpEntity<String>(headers);

				String access_token_url = "http://"+serverAddress+":"+serverPort+"/oauth/token";
				access_token_url += "?username="+login.getLoginid()+"&password="+login.getPwd()+"";
				access_token_url += "&grant_type=password";

				ResponseEntity<LoginTocken> tockens = rest.exchange(access_token_url, HttpMethod.POST, request, LoginTocken.class);
 
				LoginTocken tocken = tockens.getBody();
				
				
			    login.setAccess_tocken(tocken.getAccess_token()); 
			 
			 
			 
			 return login;
		 }
		 return null;
	}
	
	@PostMapping("/resetpwd")
	public ResponceMessage resetPwd(@RequestBody UserLogin loginid)
	{
		loginid.setPwd(passwordEncoder.encode(Generation.getDefaultPwd()));
		return service.resetPwd(loginid);
	}
	
	@PostMapping("/search")
	public List<UserLogin> search(@RequestBody UserLogin user)
	{
		List<UserLogin> logins = new ArrayList<UserLogin>();
		logins.clear();
		service.search(user)
			   .stream()
			   .forEach(x->
			   {
				   logins.add(new UserLogin(x.getId(), x.getLoginId(), x.getEmplId(), x.getEmailId(), x.getPwd(), x.getCreatedBy(),x.getIsActive()));
			   });
		return logins;
	} 
	
	@PostMapping("/updatelogin")
	public ResponceMessage updateDetails(@RequestBody UpdateLogin updateLogin) throws ParseException
	{
		if(updateLogin.getPwd() != null && !updateLogin.getPwd().equals(""))
		{
			updateLogin.setPwd(passwordEncoder.encode(updateLogin.getPwd()));
		}
		return service.updatelogin(updateLogin);
	}
}
