package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.ModelRifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRifaRepository extends JpaRepository<ModelRifa, Long> {
}
