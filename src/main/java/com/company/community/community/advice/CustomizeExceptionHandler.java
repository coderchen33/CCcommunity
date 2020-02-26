package com.company.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.company.community.community.dto.resultDTO;
import com.company.community.community.exception.CustomizeErrorCode;
import com.company.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通用异常处理
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            resultDTO resultDTO = null;
            //返回JSON
            if(ex instanceof CustomizeException){
                resultDTO =  resultDTO.errorOf((CustomizeException) ex);
            }else{
                resultDTO =  resultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setCharacterEncoding("utf-8");
                response.setStatus(200);
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else{
            //错误页面跳转
            if(ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
