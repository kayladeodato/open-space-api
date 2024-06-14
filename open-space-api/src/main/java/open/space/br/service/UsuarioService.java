package open.space.br.service;

import open.space.br.model.UsuarioEntity;
import open.space.br.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Integer alterar(Integer id, UsuarioEntity entity) {
        return gravar(id, entity);
    }

    public Integer incluir(UsuarioEntity entity) {
        return gravar(null, entity);
    }

    private Integer gravar(Integer id, UsuarioEntity entity) {

        if (entity.getNome() == null) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        if (entity.getNome().isEmpty() || entity.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }

        // Atribui a bd entity como a entidade que está vindo no parâmetro
        UsuarioEntity dbEntity = entity;

        // Se o id for diferente de nulo, faz a busca pelo id, se estiver correto, atribui o nome ao que acabou de receber
        if (id != null) {
            dbEntity = buscar(id);

            if (dbEntity.getId() != null) {
                dbEntity.setNome(entity.getNome());
            }
        }

        return repository.save(entity).getId();
    }

    private UsuarioEntity buscar(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NullPointerException("Usuário não encontrado pelo id " + " " +
                "informado."));
    }
}
