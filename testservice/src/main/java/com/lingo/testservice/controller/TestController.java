package com.lingo.testservice.controller;

import com.lingo.testservice.model.dto.request.ReqTestDTO;
import com.lingo.testservice.model.dto.response.ResTestDTO;
import com.lingo.testservice.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @PostMapping("/add")
    public ResTestDTO add(@RequestBody ReqTestDTO dto) {
        return testService.add(dto);
    }

    @PutMapping("/update")
    public ResTestDTO update(@RequestBody ReqTestDTO dto) {
        return testService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        testService.delete(id);
    }

    @GetMapping("/all")
    public List<ResTestDTO> getAll() {
        return testService.getAll();
    }

    @GetMapping("/one/{id}")
    public ResTestDTO getOne(@PathVariable long id) throws Exception {
        return testService.getOne(id);
    }
}
