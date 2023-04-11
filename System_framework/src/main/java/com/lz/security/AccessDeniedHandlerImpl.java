package com.lz.security;

import com.alibaba.fastjson.JSON;
import com.lz.domain.ResponseResult;
import com.lz.enums.AppHttpCodeEnum;
import com.lz.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：异常处理
 *
 * @Author: 李振
 * @Date: 2022/12/15 14:10
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //相应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
