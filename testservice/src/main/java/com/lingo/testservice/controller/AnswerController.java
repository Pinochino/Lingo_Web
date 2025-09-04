package com.lingo.testservice.controller;

import com.lingo.testservice.model.dto.request.ReqAnswerDTO;
import com.lingo.testservice.model.dto.response.ResAnswerDTO;
import com.lingo.testservice.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/add")
    public ResAnswerDTO add(@RequestBody ReqAnswerDTO dto) {
        return answerService.add(dto);
    }

    @PutMapping("/update")
    public ResAnswerDTO update(@RequestBody ReqAnswerDTO dto) {
        return answerService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        answerService.delete(id);
    }

    @GetMapping("/all")
    public List<ResAnswerDTO> getAll() {
        return answerService.getAll();
    }

    @GetMapping("/one/{id}")
    public ResAnswerDTO getOne(@PathVariable long id) throws Exception {
        return answerService.getOne(id);
    }
}
