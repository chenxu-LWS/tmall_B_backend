package com.loveunited.tmall_b_backend.controller.login;

import static com.loveunited.tmall_b_backend.common.constants.Constants.COOKIE_KEY;
import static com.loveunited.tmall_b_backend.common.constants.Constants.SESSION_KEY;

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
            // ??????
            loginService.login(dto.getName(), dto.getPassword());
            // ??????cookie
            Cookie cookie = new Cookie(COOKIE_KEY,dto.getName());
            cookie.setMaxAge(COOKIE_TIME_OUT);
            cookie.setPath("/");
            resp.addCookie(cookie);
            // ??????session
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
        // ??????session
        req.getSession().invalidate();
        // ??????cookie
        Cookie[] cookies = req.getCookies();
        if (null==cookies) {
            logger.error("cookie????????????");
        } else {
            for(Cookie cookie : cookies){
                //??????????????????cookie?????????value?????????null???????????????????????????0??????????????????cookie?????????????????????????????????
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
        logger.info("??????isLogin");
        final IsLoginDTO isLoginDTO = new IsLoginDTO();
        if (req.getSession().getAttribute(SESSION_KEY) != null) {// ??????session????????????????????????????????????
            isLoginDTO.setIsLogin(true);
            isLoginDTO.setUserName((String) req.getSession().getAttribute(SESSION_KEY));
            return new ReturnObject(true, isLoginDTO, 0);
        }
        // ??????cookie
        boolean isLogin = false;
        Cookie[] cs =  req.getCookies();
        if(cs != null && cs.length > 0) {
            for(Cookie c : cs) {
                if(COOKIE_KEY.equals(c.getName())) {
                    logger.info("COOKIE??????key: " + c.getName());
                    logger.info("COOKIE??????value: " + c.getValue());
                    req.getSession().setAttribute(SESSION_KEY, c.getValue());
                    isLogin = true;
                }
            }
        }
        logger.info("????????????sessionID: " + req.getRequestedSessionId());
        logger.info("???????????????session???userName?????????: " + req.getSession().getAttribute(SESSION_KEY));
        if (isLogin) {
            isLoginDTO.setIsLogin(true);
            isLoginDTO.setUserName((String) req.getSession().getAttribute(SESSION_KEY));
        } else {
            isLoginDTO.setIsLogin(false);
        }
        return new ReturnObject(true, isLoginDTO, 0);
    }
}
