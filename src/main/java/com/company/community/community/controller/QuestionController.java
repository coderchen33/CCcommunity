package com.company.community.community.controller;

import com.company.community.community.dto.QuestionDTO;
import com.company.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public String question(@PathVariable(name = "questionId") Long questionId,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        questionService.incView(questionId);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
