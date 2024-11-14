package com.gym.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gym.app.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}