package open.space.br.service;

import jakarta.persistence.EntityNotFoundException;
import open.space.br.model.UsuarioEntity;
import open.space.br.model.UsuarioRequest;
import open.space.br.model.UsuarioResponse;
import open.space.br.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Integer alterar(Integer id, UsuarioRequest request) {
        return gravar(id, request);
    }

    public Integer incluir(UsuarioRequest request) {
        return gravar(null, request);
    }

    // Adicionando uma camada para transferência de dados (DTO)
    public UsuarioResponse buscar(Integer id) {
        return converter(buscarEntity(id));
    }

    public void ativar(Integer id ){
        UsuarioResponse usuario = buscar(id);
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome(usuario.getNome());
        usuarioRequest.setEmail(usuario.getEmail());
        usuarioRequest.setTipoUsuario(usuario.getTipoUsuario());
        usuarioRequest.setAlteradEm(LocalDateTime.now());
        usuarioRequest.setAtivo(true);
        alterar(id, usuarioRequest);
    }

    public void inativar(Integer id ){
        UsuarioResponse usuario = buscar(id);
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome(usuario.getNome());
        usuarioRequest.setEmail(usuario.getEmail());
        usuarioRequest.setTipoUsuario(usuario.getTipoUsuario());
        usuarioRequest.setAlteradEm(LocalDateTime.now());
        usuarioRequest.setAtivo(false);
        alterar(id, usuarioRequest);
    }

    public List<UsuarioResponse> listar() {
        List<UsuarioResponse> response = repository.findAll().stream().map(this::converter).toList();
        return response;
    }

    private Integer gravar(Integer id, UsuarioRequest request) {

        if (request.getNome() == null) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        if (request.getNome().isEmpty() || request.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        UsuarioEntity entity = null;

        if (id != null) {
            entity = buscarEntity(id);
            if (entity == null) {
                throw new EntityNotFoundException("Usuário não encontrado.");
            }
        }
        else {
            entity = new UsuarioEntity();
        }

        // Protege a entidade no banco de dados
        BeanUtils.copyProperties(request, entity);

        if (id != null) {
            entity.setAlteradoEm(LocalDateTime.now());
        }
        else {
            entity.setCriadoEm(LocalDateTime.now());
        }
        return repository.save(entity).getId();
    }

    // Acessando a entidade cadastrada no banco de dados
    private UsuarioEntity buscarEntity(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NullPointerException("Usuário não encontrado pelo id " + " " +
                "informado."));
    }

    private UsuarioResponse converter(UsuarioEntity entity) {
        UsuarioResponse response = new UsuarioResponse();

        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
