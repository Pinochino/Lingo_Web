package com.lingo.testservice.repository;

import com.lingo.testservice.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
