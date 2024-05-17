package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.ModelLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelLoginRepositorio extends JpaRepository<ModelLogin, Long> {
}
