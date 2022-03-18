package com.loveunited.tmall_b_backend.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.login.dto.IsLoginDTO;
import com.loveunited.tmall_b_backend.controller.login.dto.UserDTO;
import com.loveunited.tmall_b_backend.service.login.LoginService;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Controller
public class LoginController {

    Logger logger = Logger.getLogger(LoginController.class);

    private static final Integer COOKIE_TIME_OUT = 24 * 60 * 60;
    private static final String COOKIE_KEY = "current_user_cookie";
    private static final String SESSION_KEY = "current_user_session";


    @Autowired
    LoginService loginService;

    @PostMapping("/api/register")
    @ResponseBody
    public ReturnObject register(@RequestBody UserDTO dto) {
        if (isNotValidDTO(dto)) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            Integer result = loginService.register(dto.getName(), dto.getPassword());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ReturnObject login(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserDTO dto) {
        if (isNotValidDTO(dto)) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            // 登陆
            loginService.login(dto.getName(), dto.getPassword());
            // 设置cookie
            Cookie cookie = new Cookie(COOKIE_KEY,dto.getName());
            cookie.setMaxAge(COOKIE_TIME_OUT);
            cookie.setPath("/");
            resp.addCookie(cookie);
            // 设置session
            req.getSession().setAttribute(SESSION_KEY, dto.getName());
            return new ReturnObject(true, null, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    private boolean isNotValidDTO(UserDTO dto) {
        return dto == null || dto.getName() == null || dto.getPassword() == null
                || dto.getName().length() > 50 || dto.getPassword().length() > 50;
    }

    @RequestMapping("/api/logout")
    @ResponseBody
    public ReturnObject logout(HttpServletRequest req, HttpServletResponse resp) {
        // 删除session
        req.getSession().invalidate();
        // 删除cookie
        Cookie[] cookies = req.getCookies();
        if (null==cookies) {
            logger.error("cookie删除失效");
        } else {
            for(Cookie cookie : cookies){
                //如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
                if(cookie.getName().equals(COOKIE_KEY)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        return new ReturnObject(true, null, 0);
    }

    @RequestMapping("/api/isLogin")
    @ResponseBody
    public ReturnObject isLogin(HttpServletRequest req) {
        logger.info("调用isLogin");
        final IsLoginDTO isLoginDTO = new IsLoginDTO();
        if (req.getSession().getAttribute(SESSION_KEY) != null) {// 查询session，如果已经登录了直接返回
            isLoginDTO.setIsLogin(true);
            isLoginDTO.setUserName((String) req.getSession().getAttribute(SESSION_KEY));
            return new ReturnObject(true, isLoginDTO, 0);
        }
        // 查询cookie
        boolean isLogin = false;
        Cookie[] cs =  req.getCookies();
        if(cs != null && cs.length > 0) {
            for(Cookie c : cs) {
                if(COOKIE_KEY.equals(c.getName())) {
                    logger.info("COOKIE中的key: " + c.getName());
                    logger.info("COOKIE中的value: " + c.getValue());
                    req.getSession().setAttribute(SESSION_KEY, c.getValue());
                    isLogin = true;
                }
            }
        }
        logger.info("当前请求sessionID: " + req.getRequestedSessionId());
        logger.info("当前请求的session的userName更新为: " + req.getSession().getAttribute(SESSION_KEY));
        if (isLogin) {
            isLoginDTO.setIsLogin(true);
            isLoginDTO.setUserName((String) req.getSession().getAttribute(SESSION_KEY));
        } else {
            isLoginDTO.setIsLogin(false);
        }
        return new ReturnObject(true, isLoginDTO, 0);
    }
}
