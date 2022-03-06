package com.loveunited.tmall_b_backend.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.controller.login.dto.UserDTO;
import com.loveunited.tmall_b_backend.exception.BizException;
import com.loveunited.tmall_b_backend.service.login.LoginService;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Controller
public class LoginController {

    private static final Integer COOKIE_TIME_OUT = 24 * 60 * 60;
    private static final String COOKIE_KEY = "current_user_cookie";

    @Autowired
    LoginService loginService;

    @PostMapping("/api/*/register")
    @ResponseBody
    public ReturnObject register(@RequestBody UserDTO dto) {
        ReturnObject returnObject = new ReturnObject();
        try {
            Integer result = loginService.register(dto.getName(), dto.getPassword());
            returnObject.setResult(result);
            returnObject.setSuccess(true);
            returnObject.setCode(0);
            returnObject.setMessage(null);
        } catch (BizException e) {
            returnObject.setResult(null);
            returnObject.setSuccess(false);
            returnObject.setCode(e.getCode());
            returnObject.setMessage(e.getMessage());
            return returnObject;
        }
        return returnObject;
    }

    @PostMapping("/api/*/login")
    @ResponseBody
    public ReturnObject login(HttpServletRequest req, HttpServletResponse resp, @RequestBody UserDTO dto) {
        ReturnObject returnObject = new ReturnObject();
        try {
            // 登陆
            loginService.login(dto.getName(), dto.getPassword());
            returnObject.setSuccess(true);
            returnObject.setCode(0);
            // 设置cookie
            Cookie cookie = new Cookie(COOKIE_KEY,dto.getName());
            cookie.setMaxAge(COOKIE_TIME_OUT);
            cookie.setPath("/");
            resp.addCookie(cookie);
        } catch (BizException e) {
            returnObject.setSuccess(false);
            returnObject.setMessage(e.getMessage());
            returnObject.setCode(e.getCode());
        }
        return returnObject;
    }

    @RequestMapping("/api/*/logout")
    @ResponseBody
    public ReturnObject logout(HttpServletRequest req, HttpServletResponse resp) {
        // 删除session
        req.getSession().invalidate();
        // 删除cookie
        Cookie[] cookies = req.getCookies();
        if (null==cookies) {
            System.out.println("cookie删除失效");
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
}
