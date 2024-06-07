// VendaRepository.java
package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}