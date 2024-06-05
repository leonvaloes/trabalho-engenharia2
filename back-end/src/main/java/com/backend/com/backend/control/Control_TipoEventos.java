package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.TipoEvento;
import com.backend.com.backend.model.services.ModelTipoEventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/TipoEventos")
public class Control_TipoEventos {

    private static final Logger logger = LoggerFactory.getLogger(Control_TipoEventos.class);

    @Autowired
    private ModelTipoEventoService tipoEventoService;

    @PostMapping("/add-Tipoevento")
    public ResponseEntity<Object> salvarTipoEvento(@RequestBody TipoEvento tipoEvento) {
        logger.info("Recebido pedido para salvar um novo tipo de evento");
        try {
            return new ResponseEntity<>(tipoEventoService.salvar(tipoEvento), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao tentar salvar um novo tipo de evento", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar salvar um novo tipo de evento");
        }
    }

    @GetMapping("/get-all-Tipoevento")
    public ResponseEntity<Object> getAllTipoEventos() {
        logger.info("Recebido pedido para buscar todos os tipos de evento");
        try {
            if (tipoEventoService.findAllTipoEvento() != null) {
                return new ResponseEntity<>(tipoEventoService.findAllTipoEvento(), HttpStatus.OK);
            } else {
                logger.warn("Nenhum tipo de evento encontrado");
                return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar TipoEvento.\"}");
            }
        } catch (Exception e) {
            logger.error("Erro ao tentar buscar todos os tipos de evento", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar buscar todos os tipos de evento");
        }
    }

    @GetMapping("/get-um-Tipoevento")
    public ResponseEntity<Object> getUmTipoEvento(@RequestParam("id") Long id) {
        logger.info("Recebido pedido para buscar um tipo de evento pelo ID");
        try {
            return new ResponseEntity<>(tipoEventoService.getumTipoEvento(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao tentar buscar um tipo de evento pelo ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar buscar um tipo de evento pelo ID");
        }
    }
}
