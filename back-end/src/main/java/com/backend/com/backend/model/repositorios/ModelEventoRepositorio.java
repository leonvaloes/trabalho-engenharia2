package com.backend.com.backend.model.repositorios;


import com.backend.com.backend.model.entidades.EventosModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ModelEventoRepositorio extends JpaRepository<EventosModel, Long> {

    List<EventosModel> findAllByTipoEventoId(Long id);
    List<EventosModel> findByNomeContains(String nome);
    List<EventosModel> findByData(LocalDate data);
}