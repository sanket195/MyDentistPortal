package myDentist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myDentist.model.User;
import myDentist.model.dao.userDao;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userDao userDao;

    @Override
    public UserDetails loadUserByUsername( String username )
        throws UsernameNotFoundException, DataAccessException
    {
        User user = userDao.getUserByUsername( username );
        //System.out.println("username---"+user.getUserType());
        if( user == null )
            throw new UsernameNotFoundException( username + " is not found." );

        return user;
    }

}
