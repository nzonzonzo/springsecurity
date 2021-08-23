package com.nzo;


import com.nzo.dao.UserDao;
import com.nzo.entry.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Test
    public void password(){
        System.out.println(passwordEncoder.encode("123"));
    }

    @Test
    public void findUserTest(){
        User user = userDao.findUser("DWF");
        System.out.println(user);
    }
}
