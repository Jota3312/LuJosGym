package com.gym.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.app.entity.Fisioterapeuta;

public interface FisioterapeutaRepository extends MongoRepository<Fisioterapeuta, String> {

}