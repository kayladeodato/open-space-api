package open.space.br;

import open.space.br.model.UsuarioEntity;
import open.space.br.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartApp implements ApplicationRunner {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UsuarioEntity usuario1 = new UsuarioEntity();
        usuario1.setNome("Elis");
        usuario1.setEmail("Elis@email.com");

        repository.save(usuario1);
    }
}
