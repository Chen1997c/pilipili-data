package com.pilipili.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述： 转发请求头filter
 *
 * @author ChenJianChuan
 * @date 2019/4/25　9:54
 */
@Component
public class RequestForwardFilter extends ZuulFilter {

    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr();
        ctx.getZuulRequestHeaders().put(HTTP_X_FORWARDED_FOR, remoteAddr);
        return null;
    }
}
