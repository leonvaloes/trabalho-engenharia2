package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ModelLogin;
import com.backend.com.backend.model.repositorios.ModelLoginRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ModelLoginService {
    private final ModelLoginRepositorio loginRepositorio;

    @Autowired
    public ModelLoginService(ModelLoginRepositorio loginRepositorio) {
        this.loginRepositorio = loginRepositorio;
    }

    public ModelLogin salvar(ModelLogin login) {
        return loginRepositorio.save(login);
    }
}
