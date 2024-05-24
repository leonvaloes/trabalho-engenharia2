package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.services.ModelTipoProdutosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/TipoProdutos")
public class Control_TipoProdutos {

    private static final Logger logger = LoggerFactory.getLogger(Control_TipoProdutos.class);

    @Autowired
    private ModelTipoProdutosService tipoProdutosService;

    @PostMapping("/add-Tipoprodutos")
    public ResponseEntity<Object> SalvarumTipoProduto(@RequestBody TipoProdutos tipoProdutos) {
        logger.info("Recebido pedido para salvar um novo tipo de produto");
        try {
            return new ResponseEntity<>(tipoProdutosService.salvar(tipoProdutos), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao tentar salvar um novo tipo de produto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar salvar um novo tipo de produto");
        }
    }

    @GetMapping("/get-all-Tipoproduto")
    public ResponseEntity<Object> getallTipoProdutos() {
        logger.info("Recebido pedido para buscar todos os tipos de produto");
        try {
            if (tipoProdutosService.findallTipoProduto() != null) {
                return new ResponseEntity<>(tipoProdutosService.findallTipoProduto(), HttpStatus.OK);
            } else {
                logger.warn("Nenhum tipo de produto encontrado");
                return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar TipoProduto.\"}");
            }
        } catch (Exception e) {
            logger.error("Erro ao tentar buscar todos os tipos de produto", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar buscar todos os tipos de produto");
        }
    }

    @GetMapping("/get-um-Tipoproduto")
    public ResponseEntity<Object> getumTipoProduto(@RequestParam("id") Long id) {
        logger.info("Recebido pedido para buscar um tipo de produto pelo ID");
        try {
            return new ResponseEntity<>(tipoProdutosService.getumTipoProduto(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Erro ao tentar buscar um tipo de produto pelo ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao tentar buscar um tipo de produto pelo ID");
        }
    }
}
