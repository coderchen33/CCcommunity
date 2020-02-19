package com.company.community.community.controller;

import com.company.community.community.dto.QuestionDTO;
import com.company.community.community.mapper.QuestionMapper;
import com.company.community.community.mapper.UserMapper;
import com.company.community.community.model.Question;
import com.company.community.community.model.User;
import com.company.community.community.service.QuestionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length!=0){
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }

        List<QuestionDTO> questionDTOList = questionService.selectAll();
        model.addAttribute("questionDTOList",questionDTOList);
        return "index";
    }
}
