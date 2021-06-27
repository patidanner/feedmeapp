package com.inbound.text.feedMe.repository;

import com.inbound.text.feedMe.model.Perfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PerfilRespository extends CrudRepository<Perfil, Long> {



}
