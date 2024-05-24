package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ProdutosModel;
import com.backend.com.backend.model.services.ModelProdutosService;
import com.backend.com.backend.model.services.ModelTipoProdutosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Produtos")
public class Control_Produtos {
    private static final Logger logger = LoggerFactory.getLogger(Control_Produtos.class);

    @Autowired
    private ModelProdutosService produtosService;

    @Autowired
    private ModelTipoProdutosService tipoProdutosService;

    @PostMapping("/add-produtos")
    public ResponseEntity<String> criarProduto(@RequestBody Map<String, Object> requestBody) {
        logger.info("Recebendo requisição para criar um novo produto");
        String nome = (String) requestBody.get("nome");
        if (nome != null && !nome.isEmpty()) {
            int estoque = 0;
            Long tipoId = 0L;
            if (requestBody.containsKey("estoque")) {
                estoque = Integer.parseInt(requestBody.get("estoque").toString());
            }
            if (requestBody.containsKey("tipoId")) {
                tipoId = Long.parseLong(requestBody.get("tipoId").toString());
            }
            logger.debug("ID do tipo do produto: {}", tipoId);
            try {
                ProdutosModel produto = new ProdutosModel();
                produto.setNome(nome);
                produto.setEstoque(estoque);
                produtosService.salvarProduto(produto, tipoId);
                logger.info("Produto criado com sucesso");
                return ResponseEntity.ok("Produto criado com sucesso!");
            } catch (Exception e) {
                logger.error("Erro ao criar o produto: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o produto: " + e.getMessage());
            }
        } else {
            logger.warn("Campo 'nome' é obrigatório");
            return ResponseEntity.badRequest().body("Campo 'nome' é obrigatório.");
        }
    }

    @GetMapping("/get-um-produto")
    public ResponseEntity<Object> getumproduto(@RequestParam("id")Long id) {
        logger.info("Recebendo requisição para buscar um produto por ID");
        if(produtosService.getByid(id) != null) {
            logger.info("Produto encontrado com sucesso");
            return new ResponseEntity<>(produtosService.getByid(id), HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar achar produto");
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar produto.\"}");
        }
    }

    @GetMapping("/get-produto-nome")
    public ResponseEntity<Object> getProdutos(@RequestParam("nome") String nome) {
        logger.info("Recebendo requisição para buscar produtos por nome");
        List<Map<String, Object>> produtos = produtosService.findByNome(nome);
        if (!produtos.isEmpty()) {
            logger.info("Produtos encontrados com sucesso");
            return ResponseEntity.ok(produtos);
        } else {
            logger.warn("Nenhum produto encontrado com o nome informado");
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum produto encontrado com o nome informado.\"}");
        }
    }

    @GetMapping("/get-produto-estoque")
    public ResponseEntity<Object> getProdutosEstoque(@RequestParam("estoque") int estoque) {
        logger.info("Recebendo requisição para buscar produtos por estoque");
        List<ProdutosModel> produtos = produtosService.findByEstoque(estoque);
        if (!produtos.isEmpty()) {
            logger.info("Produtos encontrados com sucesso");
            return ResponseEntity.ok(produtos);
        } else {
            logger.warn("Nenhum produto encontrado com o estoque informado");
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum produto encontrado com o estoque informado.\"}");
        }
    }

    @PutMapping("/update-produto")
    public ResponseEntity<String> atualizarProduto(@RequestBody Map<String, Object> requestBody) {
        logger.info("Recebendo requisição para atualizar um produto");
        Long id = Long.parseLong(requestBody.get("id").toString());
        String nome = (String) requestBody.get("nome");
        int estoque = Integer.parseInt(requestBody.get("estoque").toString());
        Long tipoId = Long.parseLong(requestBody.get("tipoId").toString());

        try {
            ProdutosModel produto = produtosService.getByid(id);
            produto.setNome(nome);
            produto.setEstoque(estoque);
            produto.setTipoProdutos(tipoProdutosService.getumTipoProduto(tipoId));
            produtosService.salvar(produto);
            logger.info("Produto atualizado com sucesso");
            return ResponseEntity.ok("Produto atualizado com sucesso!");
        } catch (Exception e) {
            logger.error("Erro ao atualizar o produto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-produtos-tipoprod")
    public ResponseEntity<Object> getallbyTipoProd(@RequestParam("id") Long id) {
        logger.info("Recebendo requisição para buscar todos os produtos de um tipo");
        if(produtosService.findallbytipoProd(id) != null) {
            logger.info("Produtos encontrados com sucesso");
            return new ResponseEntity<>(produtosService.findallbytipoProd(id), HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar achar produto");
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar produto.\"}");
        }
    }

    @DeleteMapping("/delete-Produto")
    public ResponseEntity<Object> excluirProduto(@RequestParam("id") Long id) {
        logger.info("Recebendo requisição para excluir um produto");
        if (produtosService.deleteprod(id)) {
            logger.info("Produto excluído com sucesso");
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            logger.warn("Erro ao tentar excluir produto");
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }
}
