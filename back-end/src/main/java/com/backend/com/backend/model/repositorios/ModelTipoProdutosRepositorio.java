package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.TipoProdutos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTipoProdutosRepositorio extends JpaRepository<TipoProdutos,Long> {
}
