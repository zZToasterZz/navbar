package srdt.co.in.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import srdt.co.in.entities.UserLogins;

public class AuthUserDetail extends UserLogins implements UserDetails{

	private static final long serialVersionUID = 1L;

	public AuthUserDetail(UserLogins user) {
		super(user);
	}
	public AuthUserDetail() {
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	        
	    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	       
	    grantedAuthorities.add(new SimpleGrantedAuthority("read_profile"));
	    grantedAuthorities.add(new SimpleGrantedAuthority("create_profile"));
	    grantedAuthorities.add(new SimpleGrantedAuthority("delete_profile"));
	    grantedAuthorities.add(new SimpleGrantedAuthority("update_profile"));
	    return grantedAuthorities;
	}
	
	@Override
	public String getPassword() {
        
		return super.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if(super.getIsActive().equals("N"))
			return false;
		return true;
	}
}

