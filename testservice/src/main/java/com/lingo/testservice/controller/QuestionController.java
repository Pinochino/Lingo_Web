package com.lingo.testservice.controller;

import com.lingo.testservice.model.dto.request.ReqQuestionDTO;
import com.lingo.testservice.model.dto.response.ResQuestionDTO;
import com.lingo.testservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/add")
    public ResQuestionDTO add(@RequestBody ReqQuestionDTO dto) {
        return questionService.add(dto);
    }

    @PutMapping("/update")
    public ResQuestionDTO update(@RequestBody ReqQuestionDTO dto) {
        return questionService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        questionService.delete(id);
    }

    @GetMapping("/all")
    public List<ResQuestionDTO> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/one/{id}")
    public ResQuestionDTO getOne(@PathVariable long id) throws Exception {
        return questionService.getOne(id);
    }
}
