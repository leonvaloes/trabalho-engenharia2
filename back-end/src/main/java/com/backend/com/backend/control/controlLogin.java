package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelLogin;
import com.backend.com.backend.model.entidades.ModelParametrizacao;
import com.backend.com.backend.model.services.ModelLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class controlLogin {
    private final ModelLoginService loginService;

    @Autowired
    public controlLogin(ModelLoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/criar-conta")
    public ResponseEntity<ModelLogin> criarConta(@RequestBody ModelLogin login){
        ModelLogin loginCriado = loginService.salvar(login);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginCriado);
    }
}
