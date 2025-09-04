package com.lingo.testservice.service;

import com.lingo.testservice.mapper.TestMapper;
import com.lingo.testservice.model.Test;
import com.lingo.testservice.model.dto.request.ReqTestDTO;
import com.lingo.testservice.model.dto.response.ResTestDTO;
import com.lingo.testservice.repository.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestService {
    ResTestDTO add(ReqTestDTO dto);
    ResTestDTO update(ReqTestDTO dto);
    void delete(long id);
    List<ResTestDTO> getAll();
    ResTestDTO getOne(long id) throws Exception;
}

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
class TestServiceImpl implements TestService {
    TestRepository repository;
    TestMapper mapper;

    @Override
    public ResTestDTO add(ReqTestDTO dto) {
        Test entity = mapper.toTest(dto);
        entity = repository.save(entity);
        return mapper.toTestResponse(entity);
    }

    @Override
    public ResTestDTO update(ReqTestDTO dto) {
        Test entity = mapper.toTest(dto);
        entity = repository.save(entity);
        return mapper.toTestResponse(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ResTestDTO> getAll() {
        return repository.findAll().stream().map(mapper::toTestResponse).toList();
    }

    @Override
    public ResTestDTO getOne(long id) throws Exception {
        return mapper.toTestResponse(
                repository.findById(id).orElseThrow(() -> new Exception("Test not found"))
        );
    }
}
