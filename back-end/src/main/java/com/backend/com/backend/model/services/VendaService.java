// VendaService.java
package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.Venda;
import com.backend.com.backend.model.repositorios.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> getAllVendas() {
        return vendaRepository.findAll();
    }

    public Venda createVenda(Venda venda) {
        return vendaRepository.save(venda);
    }
}