package srdt.co.in.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import srdt.co.in.entities.UserLogins;
import srdt.co.in.repositories.UserLoginRepository;
import srdt.co.in.utility.AuthUserDetail;


@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserLoginRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {
    	
    	 System.out.println("loginid:"+loginid);
    	 
    	Optional<UserLogins> optionalUser = Optional.empty() ;
    	optionalUser = Optional.of(userDetailRepository.getLoginByLoginId(loginid));
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
        UserDetails userDetails = new AuthUserDetail(optionalUser.get());
        
        System.out.println("pwd:"+optionalUser.get().getPwd());
        
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}