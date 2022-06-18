package com.api.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {

}
