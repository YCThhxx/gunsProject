package com.cskaoyan.guns.rest.modular.auth.filter;

import com.cskaoyan.guns.core.base.tips.ErrorTip;
import com.cskaoyan.guns.core.exception.GunsException;
import com.cskaoyan.guns.core.util.RenderUtil;
import com.cskaoyan.guns.rest.common.exception.BizExceptionEnum;
import com.cskaoyan.guns.rest.config.properties.JwtProperties;
import com.cskaoyan.guns.rest.modular.auth.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import redis.clients.jedis.Jedis;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对客户端请求的jwt token验证过滤器
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:04
 */
public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    Jedis jedis;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (request.getServletPath().equals("/" + jwtProperties.getAuthPath())) {
//            chain.doFilter(request, response);
//            return;
//        }

        String ignorelUrl = jwtProperties.getIgnorelUrl();
        String[] strings = ignorelUrl.split(",");
        for (String split : strings) {
            if (request.getServletPath().startsWith(split)){
                chain.doFilter(request,response);
                return;
            }
        }

        final String requestHeader = request.getHeader(jwtProperties.getHeader());
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            String userId = jedis.get(authToken);
            if(StringUtils.isBlank(userId)){
                throw new GunsException(BizExceptionEnum.TOKEN_EXPIRED);
            }else {
                jedis.expire(authToken,3600);
            }

            //验证token是否过期,包含了验证jwt是否正确
            try {
                boolean flag = jwtTokenUtil.isTokenExpired(authToken);
                if (flag) {
                    RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_EXPIRED.getMessage()));
                    return;
                }
            } catch (JwtException e) {
                //有异常就是token解析失败
                RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                return;
            }
        } else {
            //header没有带Bearer字段
            RenderUtil.renderJson(response, new ErrorTip(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return;
        }
        chain.doFilter(request, response);
    }
}
