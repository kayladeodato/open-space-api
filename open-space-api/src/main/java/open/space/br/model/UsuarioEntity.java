package open.space.br.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import open.space.br.enums.TipoUsuario;

import java.time.LocalDateTime;


@Entity
@Table (name = "tb_usuario")
@Setter
@Getter
public class UsuarioEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Gerar o Id automático
    @Setter(AccessLevel.NONE) // Não deixar alterar o Id por meio do set
    private Integer id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 50)
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private boolean ativo;
    private LocalDateTime criadoEm;
    private LocalDateTime alteradoEm;

}
