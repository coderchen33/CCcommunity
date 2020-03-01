package com.company.community.community.controller;

import com.company.community.community.dto.CommentDTO;
import com.company.community.community.dto.QuestionDTO;
import com.company.community.community.enums.CommentTypeEnum;
import com.company.community.community.service.CommentService;
import com.company.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{questionId}")
    public String question(@PathVariable(name = "questionId") Long questionId, Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);

        List<CommentDTO> comments = commentService.listByTargetId(questionId, CommentTypeEnum.QUESTION);

        //增加阅读数
        questionService.incView(questionId);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("comments",comments);
        return "question";
    }
}
