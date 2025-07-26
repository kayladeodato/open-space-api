package open.space.br.model;


import lombok.Data;
import open.space.br.enums.TipoUsuario;

import java.time.LocalDateTime;


@Data
public class UsuarioResponse {

    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private boolean ativo;
    private LocalDateTime alteradEm;

}
