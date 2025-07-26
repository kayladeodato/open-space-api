package open.space.br.controller;

import open.space.br.model.UsuarioRequest;
import open.space.br.model.UsuarioResponse;
import open.space.br.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping(value = "/cadastrar")
    public void cadastrar(@RequestBody UsuarioRequest request) {
        service.incluir(request);
    }

    @GetMapping(value = "/alterar/{id}")
    public Integer alterar(@PathVariable ("id") Integer id, @RequestBody UsuarioRequest request) {
        return service.alterar(id, request);
    }

    @GetMapping({"", "/", "/listar"})
    public List<UsuarioResponse> listar() {
        return service.listar();
    }

    @GetMapping (value = "/{id}")
    public UsuarioResponse buscarPorId(@PathVariable ("id") Integer id) {
        return service.buscar(id);
    }

    @PatchMapping(value = "/ativar/{id}")
    public void ativar(@PathVariable ("id") Integer id) {
        service.ativar(id);
    }

    @PatchMapping(value = "/inativar/{id}")
    public void inativar(@PathVariable ("id") Integer id) {
        service.inativar(id);
    }




}
