package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ProdutosModel;
import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.services.ModelProdutosService;
import com.backend.com.backend.model.services.ModelTipoProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/Produtos")
public class Control_Produtos {
    @Autowired
    private ModelProdutosService produtosService;

    @PostMapping("/add-produtos")
    public ResponseEntity<String> criarProduto(@RequestBody Map<String, Object> requestBody) {
        String nome = (String) requestBody.get("nome");
        if (nome != null && !nome.isEmpty()) {
            int estoque = 0;
            Long tipoId = 0L;
            if (requestBody.containsKey("estoque")) {
                estoque = Integer.parseInt(requestBody.get("estoque").toString());
            }
            if (requestBody.containsKey("tipoId")) { // Verifique que está recebendo "tipoId"
                tipoId = Long.parseLong(requestBody.get("tipoId").toString());
            }
            System.out.println(tipoId);
            try {
                ProdutosModel produto = new ProdutosModel();
                produto.setNome(nome);
                produto.setEstoque(estoque);
                produtosService.salvarProduto(produto, tipoId);
                return ResponseEntity.ok("Produto criado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o produto: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Campo 'nome' é obrigatório.");
        }
    }

    @GetMapping("/get-um-produto")
    public ResponseEntity<Object> getumproduto(@RequestParam("id")Long id)
    {
        if(produtosService.getByid(id)!=null)
            return new ResponseEntity<>(produtosService.getByid(id),HttpStatus.OK);
        else
            return  ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar produto.\"}");
    }
    @GetMapping("/get-produto-nome")
    public ResponseEntity<Object> getProdutos(@RequestParam("nome") String nome) {
        List<ProdutosModel> produtos = produtosService.findByNome(nome);
        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(produtos);
        } else {
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum produto encontrado com o nome informado.\"}");
        }
    }
    @GetMapping("/get-produto-estoque")
    public ResponseEntity<Object> getProdutosEstoque(@RequestParam("estoque") int estoque) {
        List<ProdutosModel> produtos = produtosService.findByEstoque(estoque);
        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(produtos);
        } else {
            return ResponseEntity.badRequest().body("{\"error\": \"Nenhum produto encontrado com o nome informado.\"}");
        }
    }
    @PostMapping("/add-estoque")
    public ResponseEntity<Object> addestoque(@RequestParam("id") Long id,@RequestParam("Qtde") int Qtde)
    {
        if(produtosService.addEstoque(id,Qtde)!=null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar adicionar Quantidade desejada em produto.\"}");
    }
    @PostMapping("/Retira-estoque")
    public ResponseEntity<Object> Retirarestoque(@RequestParam("id")Long id,@RequestParam("Qtde") int Qtde)
    {
        if(produtosService.retiraEstoque(id,Qtde)!=null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar retirar Quantidade desejada em produto.\"}");
    }
    @GetMapping("/get-all-produtos-tipoprod")
    public ResponseEntity<Object> getallbyTipoProd(@RequestParam("id") Long id)
    {
        if(produtosService.findallbytipoProd(id)!=null)
            return new ResponseEntity<>(produtosService.findallbytipoProd(id),HttpStatus.OK);
        else
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar produto.\"}");
    }
    @GetMapping("/delete-Produto")
    public ResponseEntity<Object> excluirProduto(@RequestParam("id") Long id)
    {
        if(produtosService.deleteprod(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

}
