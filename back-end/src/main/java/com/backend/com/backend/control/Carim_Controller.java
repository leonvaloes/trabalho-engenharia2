package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ProdutosModel;
import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.services.ModelProdutosService;
import com.backend.com.backend.model.services.ModelTipoProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/Carim")
public class Carim_Controller {
    @Autowired
    private ModelProdutosService produtosService;
    @Autowired
    private ModelTipoProdutosService tipoProdutosService;
    @PostMapping("/add-produtos")
    public ResponseEntity<Object> SalvarProduto(@RequestBody ProdutosModel produtos)
    {
        if(produtosService.salvar(produtos)!=null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar adicionar produto.\"}");
    }
    @GetMapping("/get-um-produto")
    public ResponseEntity<Object> getumproduto(@RequestParam("id")Long id)
    {
        if(produtosService.getByid(id)!=null)
            return new ResponseEntity<>(produtosService.getByid(id),HttpStatus.OK);
        else
            return  ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar produto.\"}");
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
    @PostMapping("/add-Tipoprodutos")
    public ResponseEntity<Object> SalvarumTipoProduto(@RequestBody TipoProdutos tipoProdutos)
    {
        return new ResponseEntity<>(tipoProdutosService.salvar(tipoProdutos),HttpStatus.OK);

    }
    @GetMapping("/get-um-Tipoproduto")
    public ResponseEntity<Object> getumTipoProduto(@RequestParam("id") Long id)
    {
        return new ResponseEntity<>(tipoProdutosService.getumTipoProduto(id),HttpStatus.OK);
    }
}
