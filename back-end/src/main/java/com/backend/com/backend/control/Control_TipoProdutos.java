package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.TipoProdutos;
import com.backend.com.backend.model.services.ModelTipoProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/TipoProdutos")
public class Control_TipoProdutos {
    @Autowired
    private ModelTipoProdutosService tipoProdutosService;
    @PostMapping("/add-Tipoprodutos")
    public ResponseEntity<Object> SalvarumTipoProduto(@RequestBody TipoProdutos tipoProdutos)
    {
        return new ResponseEntity<>(tipoProdutosService.salvar(tipoProdutos), HttpStatus.OK);

    }
    @GetMapping("/get-all-Tipoproduto")
    public ResponseEntity<Object>getallTipoProdutos()
    {
        if (tipoProdutosService.findallTipoProduto()!=null)
            return new ResponseEntity<>(tipoProdutosService.findallTipoProduto(),HttpStatus.OK);
        else
            return ResponseEntity.badRequest().body("{\"error\": \"Erro ao tentar achar TipoProduto.\"}");
    }
    @GetMapping("/get-um-Tipoproduto")
    public ResponseEntity<Object> getumTipoProduto(@RequestParam("id") Long id)
    {
        return new ResponseEntity<>(tipoProdutosService.getumTipoProduto(id),HttpStatus.OK);
    }
}
