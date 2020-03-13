package life.chen.community.interceptor;

import life.chen.community.mapper.UserMapper;
import life.chen.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//2020.3.12学习内容：拦截器
//引入interceptor可以对所有的地址进行拦截
//请求地址的时候，统一去通过cookie拿token，，去数据库获取user对象放到session里面
//以便后面使用到session的地方都可以方便的使用
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //访问任何一个地址的时候，都会去读取cookie，然后在cookie中拿token，去数据库获取user对象放到session里面
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}