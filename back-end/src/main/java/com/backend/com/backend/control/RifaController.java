package com.backend.com.backend.control;

import com.backend.com.backend.model.entidades.ModelRifa;
import com.backend.com.backend.model.services.ModelRifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Rifa")
public class RifaController {

    @Autowired
    private ModelRifaService modelRifaService;
    private ModelRifaService rifaService;


    @PostMapping
    public ModelRifa createRifa(@RequestBody ModelRifa rifa) {
        return modelRifaService.save(rifa);
    }

    @GetMapping("/all")
    public List<ModelRifa> getAllRifas() {
        return modelRifaService.getAllRifas();
    }

    @RequestMapping("/api/rifas")
    public List<ModelRifa> getAlllRifas() {
        return rifaService.getAllRifas();
    }

}
