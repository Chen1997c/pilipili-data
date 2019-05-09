package com.pilipili.provider.test;

import com.netflix.discovery.converters.Auto;
import com.pilipili.provider.PiliProviderUacApplication;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.dao.UserLikeDAO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.entity.UserLike;
import com.pilipili.provider.manager.MailManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/**
 * 描述： test case
 *
 * @author ChenJianChuan
 * @date 2019/3/6　10:40
 */
@SpringBootTest(classes = PiliProviderUacApplication.class)
@RunWith(SpringRunner.class)
public class BaseTest {

    private char[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserLikeDAO userLikeDAO;

    @Test
    public void doTest() {
        for(long i=10005L;i<10052L;i++) {
            UserLike userLike = new UserLike();
            User user = new User();
            user.setId(10001L);
            userLike.setUser(user);
            User likeUser = new User();
            likeUser.setId(i);
            userLike.setUser(user);
            userLike.setLikeUser(likeUser);
            userLikeDAO.save(userLike);
        }
//        for (int i = 0; i < 100; i++) {
//            User user = new User();
//            user.setLoginName(getLoginName());
//            user.setNickName(getLoginName());
//            user.setPassword("$2a$10$HECA683ry8pnsnIEFclrX.0xZS8xX8YRyFjnHJzD2POBv8njH9O.i");
//            user.setStatusCd(1);
//            user.setAvatarUrl("/images/user/avatar/sys/random (" + (int) (Math.random() * 8) + ").jpg");
//            user.setBirthday(new Date());
//            user.setSexCode(Math.random() > 0.5 ? 1 : 2);
//            user.setSignature("lorem is 哈哈哈");
//            userDAO.save(user);
//        }
    }

    private String getLoginName() {
        int len = (int) (Math.random() * 7 + 4);
        StringBuilder loginName = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * a.length);
            loginName.append(a[index]);
        }
        return loginName.toString();
    }
}
