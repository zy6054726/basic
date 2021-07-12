package com.basic.auth.conf;

import com.basic.commons.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Mr.zhang
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnResult exceptionHandler(Exception e, HttpServletResponse response) {
        ReturnResult returnResult = new ReturnResult();
        switch (e.getMessage()) {
            case "Bad credentials":
                returnResult.setMsg("登录信息输入错误，请确认后重新输入");
                break;
            default:
                returnResult.setMsg(e.getMessage());
                break;
        }
        return returnResult;
    }
}
