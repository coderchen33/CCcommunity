package com.company.community.community.controller;

import com.company.community.community.mapper.QuestionMapper;
import com.company.community.community.model.Question;
import com.company.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String desctiption,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",desctiption);
        model.addAttribute("tag",tag);

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(desctiption == null || desctiption == ""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }

        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user ==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(desctiption);
        question.setTag(tag);
        question.setCreatorId(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.publishQuestion(question);
        return "redirect:/";
    }
}
