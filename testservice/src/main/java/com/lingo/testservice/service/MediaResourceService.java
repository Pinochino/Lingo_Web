package com.lingo.testservice.service;

import com.lingo.testservice.mapper.MediaResourceMapper;
import com.lingo.testservice.model.MediaResource;
import com.lingo.testservice.model.dto.request.ReqMediaResourceDTO;
import com.lingo.testservice.model.dto.response.ResMediaResourceDTO;
import com.lingo.testservice.repository.MediaResourceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MediaResourceService {
    ResMediaResourceDTO add(ReqMediaResourceDTO dto);
    ResMediaResourceDTO update(ReqMediaResourceDTO dto);
    void delete(long id);
    List<ResMediaResourceDTO> getAll();
    ResMediaResourceDTO getOne(long id) throws Exception;
}

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
class MediaResourceServiceImpl implements MediaResourceService {
    MediaResourceRepository repository;
    MediaResourceMapper mapper;

    @Override
    public ResMediaResourceDTO add(ReqMediaResourceDTO dto) {
        MediaResource entity = mapper.toMediaResource(dto);
        entity = repository.save(entity);
        return mapper.toMediaResponse(entity);
    }

    @Override
    public ResMediaResourceDTO update(ReqMediaResourceDTO dto) {
        MediaResource entity = mapper.toMediaResource(dto);
        entity = repository.save(entity);
        return mapper.toMediaResponse(entity);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ResMediaResourceDTO> getAll() {
        return repository.findAll().stream().map(mapper::toMediaResponse).toList();
    }

    @Override
    public ResMediaResourceDTO getOne(long id) throws Exception {
        return mapper.toMediaResponse(
                repository.findById(id).orElseThrow(() -> new Exception("MediaResource not found"))
        );
    }
}
