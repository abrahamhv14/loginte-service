package com.uaem.mex.login.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uaem.mex.login.model.entity.UsuarioLogin;

@Repository
public interface ILoginRepository extends MongoRepository<UsuarioLogin, String> {

}
