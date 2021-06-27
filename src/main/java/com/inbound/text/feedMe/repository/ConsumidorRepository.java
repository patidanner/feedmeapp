package com.inbound.text.feedMe.repository;

import com.inbound.text.feedMe.model.Consumidor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumidorRepository extends CrudRepository<Consumidor,Long> {
}
