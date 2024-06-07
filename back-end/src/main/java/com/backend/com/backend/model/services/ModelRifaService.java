package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.ModelRifa;
import com.backend.com.backend.model.repositorios.ModelRifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelRifaService {

    @Autowired
    private ModelRifaRepository modelRifaRepository;

    public ModelRifa save(ModelRifa rifa) {
        return modelRifaRepository.save(rifa);
    }

    public List<ModelRifa> getAllRifas() {
        return modelRifaRepository.findAll();
    }
    
}
