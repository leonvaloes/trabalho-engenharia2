package com.backend.com.backend.model.services;

import com.backend.com.backend.model.entidades.EventosModel;
import com.backend.com.backend.model.entidades.TipoEvento;
import com.backend.com.backend.model.repositorios.ModelEventoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelEventoService {

    @Autowired
    private ModelEventoRepositorio eventoRepo;
    @Autowired
    private ModelTipoEventoService tipoEventoService;

    public EventosModel salvarEvento(EventosModel evento, Long tipoId) {
        TipoEvento tipoEvento = tipoEventoService.getumTipoEvento(tipoId);
        evento.setTipoEvento(tipoEvento);
        return eventoRepo.save(evento);
    }

    public EventosModel salvar(EventosModel evento) {
        return eventoRepo.save(evento);
    }

    public List<EventosModel> getAll() {
        return eventoRepo.findAll();
    }

    public EventosModel getById(Long id) {
        return eventoRepo.findById(id).orElse(null);
    }

    public List<EventosModel> findAllByTipoEvento(Long id) {
        return eventoRepo.findAllByTipoEventoId(id);
    }

    public boolean deleteEvento(Long id) {
        try {
            eventoRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Map<String, Object>> findByNome(String nome) {
        List<EventosModel> eventos = eventoRepo.findByNomeContains(nome);
        List<Map<String, Object>> eventosComTipo = new ArrayList<>();

        for (EventosModel evento : eventos) {
            Map<String, Object> eventoComTipo = new HashMap<>();
            eventoComTipo.put("id", evento.getId());
            eventoComTipo.put("nome", evento.getNome());
            eventoComTipo.put("data", evento.getData());
            eventoComTipo.put("tipo", evento.getTipoEvento().getNome());
            eventosComTipo.add(eventoComTipo);
        }

        return eventosComTipo;
    }

    public List<EventosModel> findByData(LocalDate data) {
        return eventoRepo.findByData(data);
    }
}
