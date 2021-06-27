package com.inbound.text.feedMe.repository;

import com.inbound.text.feedMe.model.Staging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagingRepository extends CrudRepository<Staging, Long> {
}
