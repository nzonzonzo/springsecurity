package com.nzo.config;

import com.nzo.dao.UserDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.nzo.entry.User user = userDao.findUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名没有找到");
        }
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        return new User(user.getUsername(),user.getPassword(),list);
    }

}
