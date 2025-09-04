package com.lingo.testservice.repository;

import com.lingo.testservice.model.MediaResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaResourceRepository extends JpaRepository<MediaResource, Long> {
}
