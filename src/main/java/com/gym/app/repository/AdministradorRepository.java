package com.gym.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.app.entity.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {

}
