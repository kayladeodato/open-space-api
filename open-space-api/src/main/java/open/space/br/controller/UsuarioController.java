package open.space.br.controller;

import open.space.br.model.UsuarioEntity;
import open.space.br.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PutMapping
    public void put() {

    }

    @GetMapping
    public List<UsuarioEntity> get() {
        return service.listar();
    }

    @PostMapping
    public void post() {

    }

    @GetMapping (value = "/{id}")
    public UsuarioEntity getById(@PathVariable ("id") Integer id) {
        return service.buscar(id);
    }

    @DeleteMapping
    public void delete() {

    }

}
