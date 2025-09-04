package com.lingo.testservice.service;

import com.lingo.testservice.mapper.QuestionMapper;
import com.lingo.testservice.model.Question;
import com.lingo.testservice.model.dto.request.ReqQuestionDTO;
import com.lingo.testservice.model.dto.response.ResQuestionDTO;
import com.lingo.testservice.repository.QuestionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {
    ResQuestionDTO add(ReqQuestionDTO dto);
    ResQuestionDTO update(ReqQuestionDTO dto);
    void delete(long id);
    List<ResQuestionDTO> getAll();
    ResQuestionDTO getOne(long id) throws Exception;
}

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
class QuestionServiceImpl implements QuestionService {
    QuestionRepository repository;
    QuestionMapper mapper;

    @Override
    public ResQuestionDTO add(ReqQuestionDTO dto) {
        Question entity = mapper.toQuestion(dto);
        entity = repository.save(entity);
        return mapper.toQuestionResponse(entity);
    }

    @Override
    public ResQuestionDTO update(ReqQuestionDTO dto) {
        Question entity = mapper.toQuestion(dto);
        entity = repository.save(entity);
        return mapper.toQuestionResponse(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ResQuestionDTO> getAll() {
        return repository.findAll().stream().map(mapper::toQuestionResponse).toList();
    }

    @Override
    public ResQuestionDTO getOne(long id) throws Exception {
        return mapper.toQuestionResponse(
                repository.findById(id).orElseThrow(() -> new Exception("Question not found"))
        );
    }
}
