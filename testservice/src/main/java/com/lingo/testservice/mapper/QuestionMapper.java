package com.lingo.testservice.mapper;

import com.lingo.testservice.model.Question;

import com.lingo.testservice.model.dto.request.ReqQuestionDTO;
import com.lingo.testservice.model.dto.response.ResQuestionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toQuestion(ReqQuestionDTO request);
    ResQuestionDTO toQuestionResponse(Question question);
}
