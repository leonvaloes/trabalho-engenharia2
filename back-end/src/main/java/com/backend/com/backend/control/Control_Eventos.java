package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.EventosModel;
import com.backend.com.backend.model.services.ModelEventoService;
import com.backend.com.backend.model.services.ModelTipoEventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Eventos")
public class Control_Eventos {
    private static final Logger logger = LoggerFactory.getLogger(Control_Eventos.class);

    @Autowired
    private ModelEventoService eventoService;

    @Autowired
    private ModelTipoEventoService tipoEventoService;

    @PostMapping("/agendar-evento")
    public ResponseEntity<String> agendarEvento(@RequestBody Map<String, Object> requestBody) {
        logger.info("Recebendo requisição para agendar um novo evento");
        String nome = (String) requestBody.get("nome");
        if (nome != null && !nome.isEmpty()) {
            LocalDate data = null;
            Long tipoId = 0L;
            if (requestBody.containsKey("data")) {
                data = LocalDate.parse(requestBody.get("data").toString());

                // Verifica se a data não é anterior à data atual
                if (data.isBefore(LocalDate.now())) {
                    logger.warn("A data escolhida é anterior à data atual");
                    return ResponseEntity.badRequest().body("A data escolhida é anterior à data atual");
                }

                // Verifica se já existe um evento com a mesma data
                if (!eventoService.findByData(data).isEmpty()) {
                    logger.warn("Já existe um evento agendado para esta data");
                    return ResponseEntity.badRequest().body("Já existe um evento agendado para esta data");
                }
            } else {
                logger.warn("Campo 'data' é obrigatório");
                return ResponseEntity.badRequest().body("Campo 'data' é obrigatório.");
            }

            if (requestBody.containsKey("tipoId")) {
                tipoId = Long.parseLong(requestBody.get("tipoId").toString());
            }
            logger.debug("ID do tipo do evento: {}", tipoId);
            try {
                EventosModel evento = new EventosModel();
                evento.setNome(nome);
                evento.setData(data);
                eventoService.salvarEvento(evento, tipoId);
                logger.info("Evento agendado com sucesso");
                return ResponseEntity.ok("Evento agendado com sucesso!");
            } catch (Exception e) {
                logger.error("Erro ao agendar o evento: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao agendar o evento: " + e.getMessage());
            }
        } else {
            logger.warn("Campo 'nome' é obrigatório");
            return ResponseEntity.badRequest().body("Campo 'nome' é obrigatório.");
        }
    }

    @GetMapping("/get-um-evento")
    public ResponseEntity<Object> getUmEvento(@RequestParam("id") Long id) {
        logger.info("Recebendo requisição para buscar um evento por ID");
        EventosModel evento = eventoService.getById(id);
        if (evento != null) {
            logger.info("Evento encontrado com sucesso");
            return new ResponseEntity<>(evento, HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar achar evento");
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar evento.\"}");
        }
    }

    @GetMapping("/get-evento-nome")
    public ResponseEntity<Object> getEventos(@RequestParam("nome") String nome) {
        logger.info("Recebendo requisição para buscar eventos por nome");
        List<Map<String, Object>> eventos = eventoService.findByNome(nome);
        if (!eventos.isEmpty()) {
            logger.info("Eventos encontrados com sucesso");
            return ResponseEntity.ok(eventos);
        } else {
            logger.warn("Nenhum evento encontrado com o nome informado");
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum evento encontrado com o nome informado.\"}");
        }
    }

    @GetMapping("/get-evento-data")
    public ResponseEntity<Object> getEventosPorData(@RequestParam("data") String dataStr) {
        logger.info("Recebendo requisição para buscar eventos por data");
        LocalDate data = LocalDate.parse(dataStr);
        List<EventosModel> eventos = eventoService.findByData(data);
        if (!eventos.isEmpty()) {
            logger.info("Eventos encontrados com sucesso");
            return ResponseEntity.ok(eventos);
        } else {
            logger.warn("Nenhum evento encontrado com a data informada");
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum evento encontrado com a data informada.\"}");
        }
    }

    @PutMapping("/update-evento")
    public ResponseEntity<String> atualizarEvento(@RequestBody Map<String, Object> requestBody) {
        logger.info("Recebendo requisição para atualizar um evento");
        Long id = Long.parseLong(requestBody.get("id").toString());
        String nome = (String) requestBody.get("nome");
        LocalDate data = LocalDate.parse(requestBody.get("data").toString());
        Long tipoId = Long.parseLong(requestBody.get("tipoId").toString());

        try {
            EventosModel evento = eventoService.getById(id);
            evento.setNome(nome);
            evento.setData(data);
            evento.setTipoEvento(tipoEventoService.getumTipoEvento(tipoId));
            eventoService.salvar(evento);
            logger.info("Evento atualizado com sucesso");
            return ResponseEntity.ok("Evento atualizado com sucesso!");
        } catch (Exception e) {
            logger.error("Erro ao atualizar o evento: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o evento: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-eventos-tipoevento")
    public ResponseEntity<Object> getAllByTipoEvento(@RequestParam("id") Long id) {
        logger.info("Recebendo requisição para buscar todos os eventos de um tipo");
        List<EventosModel> eventos = eventoService.findAllByTipoEvento(id);
        if (eventos != null && !eventos.isEmpty()) {
            logger.info("Eventos encontrados com sucesso");
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar achar eventos");
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar eventos.\"}");
        }
    }

    @DeleteMapping("/delete-evento")
    public ResponseEntity<Object> excluirEvento(@RequestParam("id") Long id) {
        logger.info("Recebendo requisição para excluir um evento");
        if (eventoService.deleteEvento(id)) {
            logger.info("Evento excluído com sucesso");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar excluir evento");
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }
}

