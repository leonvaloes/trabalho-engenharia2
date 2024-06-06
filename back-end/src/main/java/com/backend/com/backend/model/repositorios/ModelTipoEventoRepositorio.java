package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTipoEventoRepositorio extends JpaRepository<TipoEvento, Long> {
}
