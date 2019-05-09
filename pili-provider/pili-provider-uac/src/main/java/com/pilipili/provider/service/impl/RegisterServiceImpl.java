package com.pilipili.provider.service.impl;

import com.pilipili.common.exception.BusinessException;
import com.pilipili.provider.dao.UserDAO;
import com.pilipili.provider.dao.UserRoleRelDAO;
import com.pilipili.provider.entity.User;
import com.pilipili.provider.manager.MailManager;
import com.pilipili.provider.service.RegisterService;
import com.pilipili.provider.utils.RedisUtil;
import com.pilipili.provider.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

/**
 * 描述： 注册业务实现
 *
 * @author ChenJianChuan
 * @date 2019/3/8　16:14
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    private final static String GRANT_TYPE = "password";

    private final static String BASIC_AUTH = "Basic d2ViOndlYg==";

    /**
     * 验证码前缀
     */
    private final static String REGISTER_EMAIL_CODE_PREFIX = "register_code:";

    /**
     * 验证码过期时间
     */
    private final static long REGISTER_EMAIL_CODE_EXPIRE = 3L;

    private final static char[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleRelDAO userRoleRelDAO;
    @Autowired
    private MailManager mailManager;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Integer checkLoginNameIsUsed(String loginName) {
        try {
            return userDAO.getByLoginNameAndStatusCdIsNot(loginName,-1) == null ? 1 : 0;
        }catch (Exception e) {
            throw new BusinessException("查询用户名是否存在失败",e);
        }
    }

    @Transactional(rollbackOn = RuntimeException.class)
    @Override
    public Integer register(RegisterVO registerVO) {
        try {
            // 验证邮箱验证码
            String key = redisUtil.getKey(registerVO.getEmail(), REGISTER_EMAIL_CODE_PREFIX);
            String code = redisUtil.getValue(key);
            if(!registerVO.getEmailCode().equalsIgnoreCase(code)) {
                return 0;
            }
            redisUtil.remove(key);
            User user = new User();
            user.setEmail(registerVO.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(registerVO.getPassword()));
            String loginName = generateLoginName();
            // 随机生成登录名 防止重复进行查询校验
            while(userDAO.getByLoginNameAndStatusCdIsNot(loginName,-1) != null) {
                loginName = generateLoginName();
            }
            user.setLoginName(loginName);
            String avatarUrl = "/images/user/avatar/sys/random (" + (int)(Math.random() * 10 + 1) +").jpg";
            user.setAvatarUrl(avatarUrl);
            User savedUser = userDAO.save(user);
            savedUser.setNickName("p站用户" + savedUser.getId());
            //保存用户
            userDAO.save(savedUser);
            //划分角色
            userRoleRelDAO.addUserRoleRel(savedUser.getId(), 10001L);
            return 1;
        }catch (Exception e) {
            throw new BusinessException("用户注册保存用户信息失败",e);
        }
    }

    @Override
    public Integer generateEmailCode(String email) {
        try {
            User user = userDAO.getByEmail(email);
            if(user != null) {
                return 0;
            }
            StringBuilder emailCode = new StringBuilder();
            int index = 0;
            int codeLength = 6;
            while(index < codeLength) {
                emailCode.append(new Random().nextInt(10));
                index++;
            }
            mailManager.sendMail("pilipili验证码","您的注册验证码是：" + emailCode, email);
            redisUtil.setValue(redisUtil.getKey(email, REGISTER_EMAIL_CODE_PREFIX), emailCode.toString(), REGISTER_EMAIL_CODE_EXPIRE);
            return 1;
        }catch (Exception e) {
            throw new BusinessException("发送验证码邮件失败",e);
        }
    }

    /**
     * 生成loginName
     */
    private String generateLoginName() {
        int len = (int) (Math.random() * 7 + 4);
        StringBuilder loginName = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * a.length);
            loginName.append(a[index]);
        }
        return loginName.toString();
    }
}
