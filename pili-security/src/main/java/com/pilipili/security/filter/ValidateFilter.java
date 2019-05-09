package com.pilipili.security.filter;

import com.pilipili.security.properties.ValidateProperties;
import com.pilipili.security.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述： 验证验证码filter
 *
 * @author ChenJianChuan
 * @date 2019/4/22　11:35
 */
@Order(1)
@WebFilter(filterName = "validateFilter", urlPatterns = "/oauth/token")
public class ValidateFilter implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    private static final String REQUEST_METHOD_POST = "POST";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())) {
                String submitCode = request.getParameter("validateCode");
            if (StringUtils.isEmpty(submitCode)) {
                response.sendError(-1, "验证码为空");
                return;
            }
            String ip = request.getHeader(ValidateProperties.HTTP_X_FORWARDED_FOR);
            if (StringUtils.isEmpty(ip)) {
                response.sendError(-1, "系统异常，请求失败");
                return;
            }
            String codeText = redisUtil.getValue(redisUtil.getKey(ip, ValidateProperties.VALIDATE_CODE_PREFIX));
            redisUtil.remove(redisUtil.getKey(ip, ValidateProperties.VALIDATE_CODE_PREFIX));
            if (submitCode.equalsIgnoreCase(codeText)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendError(-1, "验证码错误或已过期");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
