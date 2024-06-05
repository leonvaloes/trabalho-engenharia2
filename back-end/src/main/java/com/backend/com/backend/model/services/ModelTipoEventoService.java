package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.TipoEvento;
import com.backend.com.backend.model.repositorios.ModelTipoEventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelTipoEventoService {

    @Autowired
    private ModelTipoEventoRepositorio tipoEventoRepo;

    public TipoEvento salvar(TipoEvento tipoEvento) {
        return tipoEventoRepo.save(tipoEvento);
    }

    public TipoEvento getumTipoEvento(Long id) {
        return tipoEventoRepo.findById(id).get();
    }

    public List<TipoEvento> findAllTipoEvento() {
        return tipoEventoRepo.findAll();
    }
}
