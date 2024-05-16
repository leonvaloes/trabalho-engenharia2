package com.backend.com.backend.model.repositorios;

import com.backend.com.backend.model.entidades.ProdutosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelProdutosRepositorio extends JpaRepository<ProdutosModel,Long> {
}
