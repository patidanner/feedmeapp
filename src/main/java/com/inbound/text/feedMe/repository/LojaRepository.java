package com.inbound.text.feedMe.repository;

import com.inbound.text.feedMe.model.Loja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long> {

}
