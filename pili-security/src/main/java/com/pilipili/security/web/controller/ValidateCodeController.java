package com.pilipili.security.web.controller;

import com.pilipili.security.properties.ValidateProperties;
import com.pilipili.security.util.RedisUtil;
import com.pilipili.security.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 描述： 验证码control
 *
 * @author ChenJianChuan
 * @date 2019/4/22　10:53
 */
@Controller
public class ValidateCodeController {

    @Autowired
    private ValidateCodeUtil validateCodeUtil;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 过期时间5分钟
     */
    private final static long CODE_EXPIRE = 5L;


    @GetMapping("/validateImg")
    public void getValidateImg(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String ip = request.getHeader(ValidateProperties.HTTP_X_FORWARDED_FOR);
        BufferedImage image = validateCodeUtil.getImage();
        if (!StringUtils.isEmpty(ip)) {
            String value = validateCodeUtil.getText();
            redisUtil.setValue(redisUtil.getKey(ip, ValidateProperties.VALIDATE_CODE_PREFIX), value, CODE_EXPIRE);
        }
        validateCodeUtil.output(image, response.getOutputStream());
    }
}
