package com.lingo.testservice.mapper;

import com.lingo.testservice.model.Test;
import com.lingo.testservice.model.dto.request.ReqTestDTO;
import com.lingo.testservice.model.dto.response.ResTestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toTest(ReqTestDTO request);
    ResTestDTO toTestResponse(Test test);
}
