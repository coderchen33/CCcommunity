package com.company.community.community.controller;

import com.company.community.community.dto.CommentDTO;
import com.company.community.community.dto.resultDTO;
import com.company.community.community.exception.CustomizeErrorCode;
import com.company.community.community.model.Comment;
import com.company.community.community.model.User;
import com.company.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO,
                              HttpSession session){
        User user =(User)session.getAttribute("user");
        if(user == null){
            return resultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return resultDTO.okOf();
    }
}
