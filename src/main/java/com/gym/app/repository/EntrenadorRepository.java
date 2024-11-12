package com.gym.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.app.entity.Entrenador;

public interface EntrenadorRepository extends MongoRepository<Entrenador, String> {

}
