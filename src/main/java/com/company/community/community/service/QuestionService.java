package com.company.community.community.service;

import com.company.community.community.dto.PaginationDTO;
import com.company.community.community.dto.QuestionDTO;
import com.company.community.community.mapper.QuestionMapper;
import com.company.community.community.mapper.UserMapper;
import com.company.community.community.model.Question;
import com.company.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO selectAll(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if(totalCount % size ==0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        //size*(page-1)
        Integer offset = size * (page-1);
        if(offset<0){offset = 0;}
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionMapper.selectAll(offset,size);

        for (Question question : questionList) {
            User user = userMapper.selectById(question.getCreatorId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(int userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.countbyUserId(userId);

        if(totalCount % size ==0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        //size*(page-1)
        Integer offset = size * (page-1);
        if(offset<0){offset = 0;}
        List<Question> questionList = questionMapper.listbyUser(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.selectById(question.getCreatorId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer questionId) {
        Question question = questionMapper.getQuestionById(questionId);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectById(question.getCreatorId());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
