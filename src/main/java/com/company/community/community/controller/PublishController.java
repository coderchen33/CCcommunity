package com.company.community.community.controller;

import com.company.community.community.dto.QuestionDTO;
import com.company.community.community.model.Question;
import com.company.community.community.model.User;
import com.company.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired QuestionService questionService;

    @GetMapping("/publish/{questionId}")
    public String edit(@PathVariable(name = "questionId") Integer questionId,
                       Model model){
        QuestionDTO question = questionService.getQuestionById(questionId);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("questionId",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String desctiption,
            @RequestParam("tag") String tag,
            @RequestParam("questionId") Integer questionId,
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
        question.setId(questionId);
        questionService.createOrUpdateQuestion(question);
        return "redirect:/";
    }
}
