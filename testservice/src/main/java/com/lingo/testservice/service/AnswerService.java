package com.lingo.testservice.service;

import com.lingo.testservice.mapper.AnswerMapper;
import com.lingo.testservice.model.Answer;
import com.lingo.testservice.model.dto.request.ReqAnswerDTO;
import com.lingo.testservice.model.dto.response.ResAnswerDTO;
import com.lingo.testservice.repository.AnswerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AnswerService {
    ResAnswerDTO add(ReqAnswerDTO answerDTO);
    ResAnswerDTO update(ReqAnswerDTO answerDTO);
    void delete(long id);
    List<ResAnswerDTO> getAll();
    ResAnswerDTO getOne(long id) throws Exception;
}

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
class AnswerServiceImpl implements AnswerService{
    AnswerRepository answerRepository;
    AnswerMapper answerMapper;
    @Override
    public ResAnswerDTO add(ReqAnswerDTO answerDTO) {
        Answer answer=answerMapper.toAnswer(answerDTO);
        answer = answerRepository.save(answer);
        return answerMapper.toResAnswerDTO(answer);
    }

    @Override
    public ResAnswerDTO update(ReqAnswerDTO answerDTO) {
        Answer answer=answerMapper.toAnswer(answerDTO);
        answer = answerRepository.save(answer);
        return answerMapper.toResAnswerDTO(answer);
    }

    @Override
    public void delete(long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<ResAnswerDTO> getAll() {
        return answerRepository.findAll().stream().map(answerMapper::toResAnswerDTO).toList();
    }

    @Override
    public ResAnswerDTO getOne(long id) throws Exception {
        return answerMapper.toResAnswerDTO(
                answerRepository.findById(id).orElseThrow(()-> new Exception("Answer not found"))
        );
    }
}
